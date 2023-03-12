package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
//    @Query("SELECT * FROM user")
//    fun getAll(): List<User>

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM profile_database WHERE first_name = :firstName AND " +
            "password = :password")
    fun logIn(firstName: String, password: String): Flow<ProfileInfoEntity?>

    @Query("SELECT * FROM profile_database WHERE first_name = :firstName AND " +
            "last_Name = :lastName AND email = :email")
    fun checkProfileInDatabase(firstName: String, lastName: String, email: String): Flow<ProfileInfoEntity>

    @Insert
    fun insertProfile(vararg profile: ProfileInfoEntity)

//    @Delete
//    fun delete(user: ProfileInfoEntity)
}