package com.example.githubrepoviewervodafone.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubrepoviewervodafone.data.local.entities.RepositoryEntity

@Dao
interface ReposDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repositories: List<RepositoryEntity>)

    @Query("SELECT * FROM repository_table")
    suspend fun getAllRepos(): List<RepositoryEntity>

    @Query("DELETE FROM repository_table")
    suspend fun deleteAllRepos()
}
