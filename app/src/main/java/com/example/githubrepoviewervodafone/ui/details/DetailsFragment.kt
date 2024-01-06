package com.example.githubrepoviewervodafone.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubrepoviewervodafone.R
import com.example.githubrepoviewervodafone.databinding.FragmentDetailsBinding
import com.example.githubrepoviewervodafone.ui.repositories.RepositoriesFragmentDirections
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()
    private val detailsViewModel: DetailsViewModel by viewModels()
    lateinit var binding: FragmentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        setListeners()
        binding.lifecycleOwner = this
        binding.viewModel = detailsViewModel
        binding.issuesListner.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToIssuesFragment(
                args.owner,
                args.repo
            )
            findNavController().navigate(action)
        }
        return binding.root
    }


    private fun setListeners() {
        detailsViewModel.getRepositoryDetails(args.owner, args.repo)
            .observe(viewLifecycleOwner) { response ->
                detailsViewModel.repoDetails.value = response.data
                Picasso.get().load(response.data?.owner?.imageUrl).placeholder(R.drawable.loading)
                    .into(binding.imageViewUser)

            }
    }
}