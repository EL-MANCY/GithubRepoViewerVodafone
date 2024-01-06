package com.example.githubrepoviewervodafone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubrepoviewervodafone.data.local.entities.RepositoryEntity


@Database(
    entities = [RepositoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ReposDatabase : RoomDatabase() {
    abstract val reposDao: ReposDao
}