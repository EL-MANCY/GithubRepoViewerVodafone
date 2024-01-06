package com.example.githubrepoviewervodafone.data.remote.model

import com.example.githubrepoviewervodafone.domain.model.Issue
import com.example.githubrepoviewervodafone.domain.model.Owner
import com.google.gson.annotations.SerializedName

data class IssueDto(
    val body: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    val id: Int?,
    val state: String?,
    val title: String?,
    val user: OwnerDto?
)


