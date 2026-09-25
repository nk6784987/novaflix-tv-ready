package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeCacheDao {
    @Query("SELECT * FROM anime_cache ORDER BY timestamp DESC")
    fun getAllCachedAnime(): Flow<List<AnimeCacheEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<AnimeCacheEntity>)

    @Query("DELETE FROM anime_cache")
    suspend fun clearCache()
}
