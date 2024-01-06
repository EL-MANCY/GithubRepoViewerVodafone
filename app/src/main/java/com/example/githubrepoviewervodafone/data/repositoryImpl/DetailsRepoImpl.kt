package com.example.githubrepoviewervodafone.data.repositoryImpl

import android.util.Log
import com.example.githubrepoviewervodafone.data.remote.Apis
import com.example.githubrepoviewervodafone.data.remote.model.RepoDetailsDto
import com.example.githubrepoviewervodafone.domain.repository.DetailsRepo
import com.example.githubrepoviewervodafone.domain.model.RepositoryDetails
import com.example.githubrepoviewervodafone.utils.toRepositoryDetails
import retrofit2.Response
import javax.inject.Inject

class DetailsRepoImpl @Inject constructor(
    private val apis: Apis,
) : DetailsRepo {
    override suspend fun getRepositoryDetails(
        owner: String,
        repo: String
    ): Response<RepositoryDetails> {
        val response: Response<RepoDetailsDto> = apis.getRepositoryDetails(owner, repo)

        return if (response.isSuccessful) {
            val repoDetailsDto = response.body()
            Log.i("AAAA",response.body().toString())
            if (repoDetailsDto != null) {
                Response.success(repoDetailsDto.toRepositoryDetails())
            } else {
                response.errorBody()?.let { Log.i("AAAA", it.string()) }

                Response.error(response.errorBody(), response.raw())

            }
        } else {
            response.errorBody()?.let { Log.i("AAAA", it.string()) }
            Response.error(response.errorBody(), response.raw())
        }
    }
}
