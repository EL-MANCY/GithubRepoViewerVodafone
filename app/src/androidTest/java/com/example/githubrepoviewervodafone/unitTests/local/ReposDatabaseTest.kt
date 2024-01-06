package com.example.githubrepoviewervodafone.unitTests.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubrepoviewervodafone.data.local.ReposDatabase
import com.example.githubrepoviewervodafone.data.local.ReposDao
import com.example.githubrepoviewervodafone.data.local.entities.RepositoryEntity
import com.example.githubrepoviewervodafone.domain.model.Owner
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReposDatabaseTest {

    @Test
    fun getReposFromServer() = runBlocking {
        val (reposDatabase, reposDao) = initializeDatabase()

        val repositories = createSampleRepositories()

        reposDao.insertRepos(repositories)

        val retrievedRepositories = reposDao.getAllRepos()

        assertEquals(repositories.size, retrievedRepositories.size)
        assertEquals(repositories, retrievedRepositories)
    }

    @Test
    fun updateSavedRepos() = runBlocking {
        val (reposDatabase, reposDao) = initializeDatabase()

        val repositories = createSampleRepositories()

        reposDao.insertRepos(repositories)

        reposDao.deleteAllRepos()

        val retrievedRepositories = reposDao.getAllRepos()

        assertEquals(0, retrievedRepositories.size)
    }

    private fun initializeDatabase(): Pair<ReposDatabase, ReposDao> {
        val reposDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ReposDatabase::class.java
        ).allowMainThreadQueries().build()

        return Pair(reposDatabase, reposDatabase.reposDao)
    }

    private fun createSampleRepositories(): List<RepositoryEntity> {
        return listOf(
            RepositoryEntity("1", "REPO1", "XXXXX", Owner("11", "ALEX", "imageUrl")),
            RepositoryEntity("2", "REPO2", "YYYYYY", Owner("22", "JAMES", "imageUrl"))
        )
    }
}