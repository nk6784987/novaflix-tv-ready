package com.example.data.repository

import com.example.data.local.AnimeCacheDao
import com.example.data.local.AnimeCacheEntity
import com.example.data.model.MediaItem
import com.example.data.model.MediaType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AnimeProviderRepository(
    private val animeCacheDao: AnimeCacheDao
) {

    val cachedAnime: Flow<List<MediaItem>> = animeCacheDao.getAllCachedAnime().map { list ->
        list.map { entity ->
            MediaItem(
                id = entity.animeId,
                title = entity.title,
                posterPath = entity.posterPath,
                backdropPath = entity.posterPath,
                rating = entity.rating,
                releaseYear = entity.releaseYear,
                mediaType = MediaType.ANIME,
                genres = entity.genres.split(","),
                overview = ""
            )
        }
    }

    suspend fun refreshAnimeCache(items: List<MediaItem>) = withContext(Dispatchers.IO) {
        val entities = items.map { item ->
            AnimeCacheEntity(
                animeId = item.id,
                title = item.title,
                posterPath = item.posterPath,
                rating = item.rating,
                releaseYear = item.releaseYear,
                genres = item.genres.joinToString(",")
            )
        }
        animeCacheDao.clearCache()
        animeCacheDao.insertAll(entities)
    }
}
