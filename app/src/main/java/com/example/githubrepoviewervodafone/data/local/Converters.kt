package com.example.githubrepoviewervodafone.data.local

import androidx.room.TypeConverter
import com.example.githubrepoviewervodafone.domain.model.Owner
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromOwnerEntity(owner: Owner): String {
        return Gson().toJson(owner)
    }

    @TypeConverter
    fun toUserEntity(json: String): Owner {
        return Gson().fromJson(json, Owner::class.java)
    }
}
