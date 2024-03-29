package com.example.githubrepoviewervodafone.domain.model


data class RepositoryDetails(
    val id: String,
    val name: String,
    val description: String,
    val language: String,
    val starCount: Int,
    val watchersCount: Int,
    val forksCount: Int,
    val openIssuesCount: Int,
    val subscribersCount: Int,
    val createdAt: String,
    val owner: Owner,
)
