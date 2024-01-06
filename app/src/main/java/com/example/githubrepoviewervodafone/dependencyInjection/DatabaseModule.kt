package com.example.githubrepoviewervodafone.dependencyInjection

import android.content.Context
import androidx.room.Room
import com.example.githubrepoviewervodafone.data.local.ReposDatabase
import com.example.githubrepoviewervodafone.data.local.ReposDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context: Context,
    ): ReposDatabase {
        return Room.databaseBuilder(context, ReposDatabase::class.java, "ReposDatabase")
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: ReposDatabase): ReposDao {
        return database.reposDao
    }

}