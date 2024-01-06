package com.example.githubrepoviewervodafone.unitTests.viewModel
import android.app.Application
import com.example.githubrepoviewervodafone.data.repositoryImpl.RepositoriesImpl
import com.example.githubrepoviewervodafone.ui.repositories.RepositoryViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RepositoryViewModelTest {

    private lateinit var viewModel: RepositoryViewModel
    private lateinit var mockRepositoriesImpl: RepositoriesImpl

    @Before
    fun setUp() {
        mockRepositoriesImpl = mockk(relaxed = true)
        viewModel = RepositoryViewModel(mockRepositoriesImpl, Application())
    }

    @Test
    fun getAllRepositoriesShouldReturnSuccess() = runBlocking {
        // Arrange
        coEvery { mockRepositoriesImpl.getRepositories(any()) } returns Response.success(emptyList())

        // Act
        viewModel.getAllRepositories().observeForever { }

        // Assert
        assertEquals(false, viewModel.isLoading)
    }

    @Test
    fun loadNextPageShouldIncrementCurrentPageAndCalLGetAllRepositories() = runBlocking {
        // Arrange
        val initialPage = viewModel.currentPage

        // Act
        viewModel.loadNextPage()

        // Assert
        assertEquals(initialPage + 1, viewModel.currentPage)
    }
}
