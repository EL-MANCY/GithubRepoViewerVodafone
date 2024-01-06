package com.example.githubrepoviewervodafone.data.remote.model

import com.example.githubrepoviewervodafone.domain.model.RepositoryDetails
import com.example.githubrepoviewervodafone.domain.model.Owner
import com.google.gson.annotations.SerializedName

data class RepositoryDto(
    @SerializedName("description") val description: String?,
    @SerializedName("fork") val fork: Boolean?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("owner") val owner: OwnerDto?,
)