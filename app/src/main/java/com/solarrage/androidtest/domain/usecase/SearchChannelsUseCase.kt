package com.solarrage.androidtest.domain.usecase

import com.solarrage.androidtest.domain.model.Channel
import com.solarrage.androidtest.domain.repository.ChannelRepository

class SearchChannelsUseCase(
    private val repository: ChannelRepository
) {
    suspend fun execute(query: String): List<Channel> {
        val channels = repository.getChannels()

        val trimmed = query.trim()
        if(trimmed.isEmpty()) return channels

        return channels.filter {channel ->
            channel.category.subCategory.name.contains(trimmed, ignoreCase = true) ||
                    channel.category.subCategory.subCategory.name.contains(trimmed, ignoreCase = true)
        }
    }
}