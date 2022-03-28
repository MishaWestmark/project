package com.westmark.unsplash.networking

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class RemoteKey(
    @PrimaryKey
    val id: String,
    val nextKey: Int?,
    val prevKey: Int?
)