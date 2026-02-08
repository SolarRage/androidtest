package com.solarrage.androidtest.data.source

import android.content.res.AssetManager
import com.solarrage.androidtest.data.model.ChannelDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ChannelLocalDataSourceImpl(
    private val assetManager: AssetManager
) : ChannelLocalDataSource {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private var cache: List<ChannelDto>? = null

    override suspend fun loadChannels(): List<ChannelDto> {
        return cache ?: loadAndCache()
    }

    private suspend fun loadAndCache(): List<ChannelDto> =
        withContext(Dispatchers.IO) {

            val text = assetManager
                .open(FILE_NAME)
                .bufferedReader()
                .use { it.readText() }

            val parsed = json.decodeFromString<List<ChannelDto>>(text)
            cache = parsed
            return@withContext parsed
        }

    private companion object {
        const val FILE_NAME = "channels.json"
    }
}