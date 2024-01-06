package com.example.githubrepoviewervodafone.ui.issues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepoviewervodafone.R
import com.example.githubrepoviewervodafone.databinding.IssueItemBinding
import com.example.githubrepoviewervodafone.domain.model.Issue
import com.example.githubrepoviewervodafone.utils.DiffUtilCallBack
import com.squareup.picasso.Picasso

class IssuesAdapter() : RecyclerView.Adapter<IssuesAdapter.MyViewHolder>() {

    private var issuesList = emptyList<Issue>()

    inner class MyViewHolder(val binding: IssueItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = IssueItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val issue = issuesList[position]
        with(holder.binding) {
            titleTextView.text = issue.title
            bodyTextView.text = issue.body
            createdAtTextView.text = issue.createdAt
            userTextView.text = "By: ${issue.user.name}"
            stateTextView.text = "State: ${issue.state}"
            Picasso.get().load(issue.user.imageUrl).placeholder(R.drawable.loading)
                .into(imageViewUser)
        }
    }

    override fun getItemCount(): Int = issuesList.size

    fun updateIssues(newIssuesList: List<Issue>) {
        val diffUtilCallback = DiffUtilCallBack(issuesList, newIssuesList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        issuesList = newIssuesList
        diffResult.dispatchUpdatesTo(this)
    }
}
