package com.example.githubrepoviewervodafone.ui.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubrepoviewervodafone.baseViewModel.BaseViewModel
import com.example.githubrepoviewervodafone.data.repositoryImpl.RepositoriesImpl
import com.example.githubrepoviewervodafone.domain.model.Repository
import com.example.githubrepoviewervodafone.domain.repository.RepositoriesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val repo: RepositoriesImpl,
    application: Application
) : BaseViewModel(application) {

    var currentPage = 1
    var isLoading = false


    fun getAllRepositories() = handleFlowResponse {
        isLoading = true
        repo.getRepositories(currentPage).also {
            isLoading = false
        }
    }

    fun loadNextPage() {
        if (!isLoading) {
            currentPage++
            getAllRepositories()
        }
    }
}