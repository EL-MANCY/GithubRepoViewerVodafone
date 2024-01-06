package com.example.githubrepoviewervodafone.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.fragment.app.Fragment
import com.example.githubrepoviewervodafone.data.local.entities.RepositoryEntity
import com.example.githubrepoviewervodafone.data.remote.model.IssueDto
import com.example.githubrepoviewervodafone.data.remote.model.OwnerDto
import com.example.githubrepoviewervodafone.data.remote.model.RepoDetailsDto
import com.example.githubrepoviewervodafone.data.remote.model.RepositoryDto
import com.example.githubrepoviewervodafone.domain.model.Issue
import com.example.githubrepoviewervodafone.domain.model.Owner
import com.example.githubrepoviewervodafone.domain.model.Repository
import com.example.githubrepoviewervodafone.domain.model.RepositoryDetails
import dagger.hilt.android.internal.Contexts.getApplication

fun Fragment.hasInternetConnection(application: Application): Boolean {
    val connectivityManager = getApplication(application).getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}

fun IssueDto.toIssue() = Issue(
    id = id.toString(),
    title = title ?: "",
    body = body ?: "",
    createdAt = createdAt ?: "",
    state = state ?: "",
    user = user?.toOwner() ?: Owner.createDefault()
)

fun OwnerDto.toOwner() = Owner(
    id = id.toString(),
    name = login ?: "",
    imageUrl = avatarUrl ?: ""
)
fun RepoDetailsDto.toRepositoryDetails() = RepositoryDetails(
    id = id.toString(),
    name = name ?: "",
    description = description ?: "",
    owner = owner?.toOwner() ?: Owner.createDefault(),
    starCount = stargazersCount ?: 0,
    openIssuesCount = openIssuesCount ?: 0,
    forksCount = forksCount ?: 0,
    watchersCount = watchersCount ?: 0,
    language = language ?: "",
    createdAt = createdAt ?: "",
    subscribersCount = subscribersCount ?: 0,
)

fun RepositoryEntity.toRepository() = Repository(
    id = id,
    name = name,
    description = description,
    owner = owner
)
fun RepositoryDto.toRepositoryEntity() = RepositoryEntity(
    id = id.toString(),
    name = name.toString() ,
    description = description.toString(),
    owner = owner?.toOwner() ?: Owner.createDefault(),
)




