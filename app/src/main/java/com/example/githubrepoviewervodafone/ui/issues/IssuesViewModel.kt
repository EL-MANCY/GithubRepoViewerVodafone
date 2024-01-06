package com.example.githubrepoviewervodafone.ui.issues

import android.app.Application
import com.example.githubrepoviewervodafone.baseViewModel.BaseViewModel
import com.example.githubrepoviewervodafone.data.repositoryImpl.IssuesRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    private val issues: IssuesRepoImpl,
    application: Application
) : BaseViewModel(application) {

    fun getIssues(owner: String, repo: String) = handleFlowResponse {
        issues.getIssues(owner, repo)
    }


}
