package com.example.githubrepoviewervodafone.data.repositoryImpl

import com.example.githubrepoviewervodafone.data.local.ReposDao
import com.example.githubrepoviewervodafone.data.local.entities.RepositoryEntity
import com.example.githubrepoviewervodafone.data.remote.Apis
import com.example.githubrepoviewervodafone.data.remote.model.RepositoryDto
import com.example.githubrepoviewervodafone.domain.repository.RepositoriesRepo
import com.example.githubrepoviewervodafone.domain.model.Repository
import com.example.githubrepoviewervodafone.utils.toRepository
import com.example.githubrepoviewervodafone.utils.toRepositoryEntity
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class RepositoriesImpl @Inject constructor(
    private val apis: Apis,
    private val reposDao: ReposDao,
) : RepositoriesRepo {
    private val perPage = 10

    override suspend fun getRepositories(page: Int): Response<List<Repository>> {
        return try {
            val repositoriesFromApi = getReposFromServer(page)
            repositoriesFromApi.body()?.let { updateSavedRepos(it) }
            val repositoriesFromDb = getSavedRepos()
            Response.success(repositoriesFromDb)
        } catch (exception: Exception) {
            val repositoriesFromDb = getSavedRepos()
            Response.success(repositoriesFromDb)
        }
    }

    private suspend fun getReposFromServer(page: Int): Response<List<RepositoryEntity>> {
        val response: Response<List<RepositoryDto>> = apis.getRepositories(page, perPage)

        return if (response.isSuccessful) {
            val repositoryDtoList = response.body()
            if (repositoryDtoList != null) {
                val repositoryEntityList = repositoryDtoList.map { it.toRepositoryEntity() }
                Response.success(repositoryEntityList)
            } else {
                Response.error(response.errorBody(), response.raw())
            }
        } else {
            Response.error(response.errorBody(), response.raw())
        }
    }

    private suspend fun getSavedRepos(): List<Repository> {
        return reposDao.getAllRepos().map { it.toRepository() }
    }

    private suspend fun updateSavedRepos(repositories: List<RepositoryEntity>) {
        reposDao.deleteAllRepos()
        reposDao.insertRepos(repositories)
    }

}
