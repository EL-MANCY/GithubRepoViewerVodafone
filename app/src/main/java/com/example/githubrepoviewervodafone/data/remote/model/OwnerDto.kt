package com.example.githubrepoviewervodafone.data.remote.model

import com.example.githubrepoviewervodafone.domain.model.Owner
import com.google.gson.annotations.SerializedName

data class OwnerDto(
    val login: String?,
    val id: Long?,
    @SerializedName("avatar_url") val avatarUrl: String?,
)
