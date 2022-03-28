package com.westmark.unsplash.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.westmark.unsplash.presentation.data.PhotoEntity

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoDatabase(photos: List<PhotoEntity>)

    @Query("SELECT * FROM ${PhotoContract.TABLE_NAME}")
    fun getPhotoDatabase(): PagingSource<Int, PhotoEntity>

    @Query("DELETE FROM ${PhotoContract.TABLE_NAME}")
    suspend fun clearAll()
}