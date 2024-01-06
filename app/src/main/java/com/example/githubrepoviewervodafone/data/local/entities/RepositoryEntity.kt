package com.example.githubrepoviewervodafone.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githubrepoviewervodafone.domain.model.Owner

@Entity(tableName = "repository_table")
data class RepositoryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val owner: Owner
)
