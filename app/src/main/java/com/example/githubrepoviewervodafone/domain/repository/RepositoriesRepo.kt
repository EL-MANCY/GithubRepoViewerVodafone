package com.example.githubrepoviewervodafone.domain.repository

import com.example.githubrepoviewervodafone.domain.model.Repository
import retrofit2.Response


interface RepositoriesRepo {
    suspend fun getRepositories(page: Int): Response<List<Repository>>
}
