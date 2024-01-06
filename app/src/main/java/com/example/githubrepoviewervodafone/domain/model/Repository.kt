package com.example.githubrepoviewervodafone.domain.model


data class Repository(
    val id: String,
    val name: String,
    val description: String,
    val owner: Owner
)
