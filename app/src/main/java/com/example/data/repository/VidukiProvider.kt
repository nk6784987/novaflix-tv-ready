package com.example.data.repository

import com.example.data.model.MediaType

enum class VidukiApi(val id: String, val displayName: String, val apiIndex: Int) {
    API_1("api1", "API 1 — Multi Server", 1),
    API_2("api2", "API 2 — Multi Language", 2),
    API_3("api3", "API 3 — Multi Embeds", 3),
    API_4("api4", "API 4 — Premium", 4);

    companion object {
        fun fromIndex(index: Int): VidukiApi = entries.find { it.apiIndex == index } ?: API_1
    }
}

object VidukiProvider {
    fun getUrl(api: VidukiApi, type: MediaType, id: String, season: Int, episode: Int): String {
        return if (type == MediaType.MOVIE) {
            "https://viduki.net/${api.apiIndex}/movie/$id"
        } else {
            "https://viduki.net/${api.apiIndex}/tv/$id/$season/$episode"
        }
    }
}
