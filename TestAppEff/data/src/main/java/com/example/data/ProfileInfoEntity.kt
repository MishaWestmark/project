package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_database")
data class ProfileInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "first_name")
    val firstname: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name= "email")
    val email: String,
    @ColumnInfo(name = "password")
    val password: String = "123"

)
