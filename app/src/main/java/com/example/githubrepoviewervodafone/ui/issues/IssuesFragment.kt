package com.example.githubrepoviewervodafone.ui.issues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepoviewervodafone.R
import com.example.githubrepoviewervodafone.data.remote.httpStatus.ApiStatus
import com.example.githubrepoviewervodafone.databinding.FragmentDetailsBinding
import com.example.githubrepoviewervodafone.databinding.FragmentIssuesBinding
import com.example.githubrepoviewervodafone.domain.model.Issue
import com.example.githubrepoviewervodafone.domain.model.Repository
import com.example.githubrepoviewervodafone.ui.repositories.RepositoriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssuesFragment : Fragment() {

    lateinit var binding: FragmentIssuesBinding

    private val args by navArgs<IssuesFragmentArgs>()
    private val issuesViewModel: IssuesViewModel by viewModels()
    private val issueAdapter by lazy { IssuesAdapter() }
    private var issueList = mutableListOf<Issue>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIssuesBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        with(binding.issuesRv) {
            adapter = issueAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        issuesViewModel.getIssues(args.owner, args.repo).observe(viewLifecycleOwner) { result ->
            when (result.status) {
                ApiStatus.SUCCESS -> {
                    result.data?.let {
                        if (it.isNotEmpty()) {
                            // Update the dataset only when new data is received
                            issueList.addAll(it)
                            issueAdapter.updateIssues(issueList)
                        }
                    }
                }

                ApiStatus.ERROR -> {
                    // Handle error if needed
                }

                ApiStatus.LOADING -> {
                    // You may choose to show a loading indicator
                }
            }
        }
    }


}