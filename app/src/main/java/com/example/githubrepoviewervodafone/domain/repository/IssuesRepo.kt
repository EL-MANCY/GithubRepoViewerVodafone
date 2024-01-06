package com.example.githubrepoviewervodafone.domain.repository

import com.example.githubrepoviewervodafone.domain.model.Issue
import retrofit2.Response

interface IssuesRepo {
    suspend fun getIssues(
        owner: String,
        repo: String
    ): Response<List<Issue>>
}