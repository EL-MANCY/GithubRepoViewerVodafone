package com.example.githubrepoviewervodafone.domain.model


data class Issue(
    val id: String,
    val title: String,
    val body: String,
    val state: String,
    val createdAt: String,
    val user: Owner
)
