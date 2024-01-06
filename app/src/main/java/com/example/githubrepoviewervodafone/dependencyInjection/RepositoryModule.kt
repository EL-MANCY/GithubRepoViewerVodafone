package com.example.githubrepoviewervodafone.dependencyInjection

import com.example.githubrepoviewervodafone.domain.repository.DetailsRepo
import com.example.githubrepoviewervodafone.domain.repository.IssuesRepo
import com.example.githubrepoviewervodafone.domain.repository.RepositoriesRepo
import com.example.githubrepoviewervodafone.data.repositoryImpl.DetailsRepoImpl
import com.example.githubrepoviewervodafone.data.repositoryImpl.IssuesRepoImpl
import com.example.githubrepoviewervodafone.data.repositoryImpl.RepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideUsersRepository(
        impl: RepositoriesImpl
    ): RepositoriesRepo

    @Binds
    abstract fun providePhotosRepository(
        impl: DetailsRepoImpl
    ): DetailsRepo

    @Binds
    abstract fun provideAlbumsRepository(
        impl: IssuesRepoImpl
    ): IssuesRepo
}