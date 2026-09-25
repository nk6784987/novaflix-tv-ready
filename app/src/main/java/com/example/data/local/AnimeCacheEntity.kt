package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_cache")
data class AnimeCacheEntity(
    @PrimaryKey val animeId: String,
    val title: String,
    val posterPath: String?,
    val rating: Double,
    val releaseYear: String,
    val genres: String, // Stored as comma-separated
    val timestamp: Long = System.currentTimeMillis()
)
