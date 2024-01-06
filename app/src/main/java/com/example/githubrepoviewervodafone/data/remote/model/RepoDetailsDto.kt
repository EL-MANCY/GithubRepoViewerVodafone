package com.example.githubrepoviewervodafone.data.remote.model

import com.google.gson.annotations.SerializedName

data class RepoDetailsDto(
    val id: Long?,
    val name: String?,
    val private: Boolean?,
    val owner: OwnerDto?,
    val description: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("stargazers_count") val stargazersCount: Int?,
    @SerializedName("watchers_count") val watchersCount: Int?,
    val language: String?,
    @SerializedName("forks_count") val forksCount: Int?,
    @SerializedName("open_issues_count") val openIssuesCount: Int?,
    @SerializedName("subscribers_count") val subscribersCount: Int?
)

