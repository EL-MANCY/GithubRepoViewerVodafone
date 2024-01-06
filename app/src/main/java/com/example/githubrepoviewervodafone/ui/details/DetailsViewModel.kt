package com.example.githubrepoviewervodafone.ui.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubrepoviewervodafone.baseViewModel.BaseViewModel
import com.example.githubrepoviewervodafone.data.repositoryImpl.DetailsRepoImpl
import com.example.githubrepoviewervodafone.domain.model.RepositoryDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepoImpl: DetailsRepoImpl,
    application: Application
) : BaseViewModel(application) {

    val repoDetails = MutableLiveData<RepositoryDetails>()


    fun getRepositoryDetails(
        owner: String,
        repo: String
    ) = handleFlowResponse {
        detailsRepoImpl.getRepositoryDetails(owner, repo)
    }

}