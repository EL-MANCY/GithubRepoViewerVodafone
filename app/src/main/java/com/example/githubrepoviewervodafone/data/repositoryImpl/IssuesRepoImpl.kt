package com.example.githubrepoviewervodafone.data.repositoryImpl

import com.example.githubrepoviewervodafone.data.remote.Apis
import com.example.githubrepoviewervodafone.data.remote.model.IssueDto
import com.example.githubrepoviewervodafone.domain.repository.IssuesRepo
import com.example.githubrepoviewervodafone.domain.model.Issue
import com.example.githubrepoviewervodafone.utils.toIssue
import retrofit2.Response
import javax.inject.Inject

class IssuesRepoImpl @Inject constructor(
    private val apis: Apis,
) : IssuesRepo {
    override suspend fun getIssues(owner: String, repo: String): Response<List<Issue>> {
        val response: Response<List<IssueDto>> = apis.getIssues(owner, repo)

        return if (response.isSuccessful) {
            val issueDtoList = response.body()
            if (issueDtoList != null) {
                val issueList = issueDtoList.map { it.toIssue() }
                Response.success(issueList)
            } else {
                Response.error(response.errorBody(), response.raw())
            }
        } else {
            Response.error(response.errorBody(), response.raw())
        }
    }
}
