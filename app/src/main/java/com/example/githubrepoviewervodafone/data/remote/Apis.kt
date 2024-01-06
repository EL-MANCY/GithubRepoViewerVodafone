package com.example.githubrepoviewervodafone.data.remote

import com.example.githubrepoviewervodafone.data.remote.model.IssueDto
import com.example.githubrepoviewervodafone.data.remote.model.RepoDetailsDto
import com.example.githubrepoviewervodafone.data.remote.model.RepositoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Apis {
    @GET("repositories")
    suspend fun getRepositories(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<RepositoryDto>>

    @GET("/repos/{owner}/{repo_name}")
    suspend fun getRepositoryDetails(
        @Path("owner") owner: String,
        @Path("repo_name") repo: String
    ): Response<RepoDetailsDto>

    @GET("/repos/{owner}/{repo}/issues")
    suspend fun getIssues(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<List<IssueDto>>
}