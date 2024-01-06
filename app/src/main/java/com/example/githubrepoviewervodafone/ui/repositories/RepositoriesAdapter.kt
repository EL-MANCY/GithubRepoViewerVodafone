package com.example.githubrepoviewervodafone.ui.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepoviewervodafone.R
import com.example.githubrepoviewervodafone.databinding.RepoItemBinding
import com.example.githubrepoviewervodafone.domain.model.Repository
import com.example.githubrepoviewervodafone.utils.DiffUtilCallBack
import com.squareup.picasso.Picasso

class RepositoriesAdapter(private val onRepoClickListener: OnRepoClickListener) :
    RecyclerView.Adapter<RepositoriesAdapter.MyViewHolder>() {

    private var repoList = emptyList<Repository>()

    inner class MyViewHolder(val binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RepoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val repository = repoList[position]
        with(holder.binding) {
            textViewRepositoryName.text = repository.name
            Picasso.get().load(repository.owner.imageUrl).placeholder(R.drawable.loading)
                .into(imageViewUser)
            textViewRepositoryDescription.text = repository.description
            root.setOnClickListener {
                repository.let {
                    onRepoClickListener.onRepoItemClick(it.owner.name,it.name)
                }
            }
        }
    }

    override fun getItemCount(): Int = repoList.size

    fun updateRepos(newReposList: List<Repository>) {
        val diffUtilCallback = DiffUtilCallBack(repoList, newReposList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        repoList = newReposList
        diffResult.dispatchUpdatesTo(this)
    }
}
