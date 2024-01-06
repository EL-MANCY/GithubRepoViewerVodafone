package com.example.githubrepoviewervodafone.domain.repository

import com.example.githubrepoviewervodafone.domain.model.RepositoryDetails
import retrofit2.Response


interface DetailsRepo {
    suspend fun getRepositoryDetails(
        owner: String,
        repo: String
    ): Response<RepositoryDetails>

}