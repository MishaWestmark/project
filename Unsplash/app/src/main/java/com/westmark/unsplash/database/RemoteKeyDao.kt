package com.westmark.unsplash.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.westmark.unsplash.networking.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: List<RemoteKey>)

    @Query("SELECT * FROM remote_key WHERE id = :id")
    suspend fun remoteKeyByQuery(id: String): RemoteKey

    @Query("DELETE FROM remote_key")
    suspend fun deleteByQuery()
}