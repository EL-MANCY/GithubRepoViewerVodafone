package com.example.githubrepoviewervodafone.ui.repositories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepoviewervodafone.data.remote.httpStatus.ApiStatus
import com.example.githubrepoviewervodafone.databinding.FragmentRepositoriesBinding
import com.example.githubrepoviewervodafone.domain.model.Repository
import com.example.githubrepoviewervodafone.utils.hasInternetConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoriesFragment : Fragment(), OnRepoClickListener {
    lateinit var binding: FragmentRepositoriesBinding
    private val repositoryViewModel: RepositoryViewModel by viewModels()
    private val repositoryAdapter by lazy { RepositoriesAdapter(this) }
    private var reposList = mutableListOf<Repository>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepositoriesBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()
        setListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (reposList.isEmpty()) {
            getRepos()
        }
    }

    private fun setupRecyclerView() {
        with(binding.reposRv) {
            adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItem >= totalItemCount && !repositoryViewModel.isLoading) {
                        repositoryViewModel.loadNextPage()
                    }
                }
            })
        }
    }

    private fun getRepos() {
        repositoryViewModel.getAllRepositories().observe(viewLifecycleOwner) { result ->
            when (result.status) {
                ApiStatus.SUCCESS -> {
                    result.data?.let {
                        if (it.isNotEmpty()) {
                            // Update the dataset only when new data is received
                            reposList.addAll(it)
                            repositoryAdapter.updateRepos(reposList)
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

    private fun setListeners() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                handleSearch(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                lifecycleScope.launchWhenResumed {
                    handleSearch(query)
                }
                return true
            }
        })
    }

    private fun handleSearch(query: String?) {
        val filteredList = reposList.filter {
            it.name.contains(
                query.orEmpty(),
                ignoreCase = true
            ) || it.owner.name.contains(query.orEmpty(), ignoreCase = true)
        }
        repositoryAdapter.updateRepos(filteredList)
    }

    override fun onRepoItemClick(owner: String, repo: String) {
        if (hasInternetConnection(requireActivity().application)) {
            findNavController().navigate(
                RepositoriesFragmentDirections.actionRepositoriesFragmentToDetailsFragment(
                    owner,
                    repo
                )
            )
        } else {
            Toast.makeText(requireContext(), "No Internet Connection!!!", Toast.LENGTH_SHORT).show()

        }
    }
}