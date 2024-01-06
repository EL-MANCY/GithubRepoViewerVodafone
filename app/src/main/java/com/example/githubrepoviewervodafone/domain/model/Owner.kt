package com.example.githubrepoviewervodafone.domain.model


data class Owner(
    val id: String,
    val name: String,
    val imageUrl: String
) {
    companion object {
        fun createDefault() = Owner("", "", "")
    }
}

