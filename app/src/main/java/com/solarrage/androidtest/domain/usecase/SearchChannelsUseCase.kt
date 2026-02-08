package com.solarrage.androidtest.domain.usecase

import com.solarrage.androidtest.domain.model.Channel
import com.solarrage.androidtest.domain.repository.ChannelRepository

class SearchChannelsUseCase(
    private val repository: ChannelRepository
) {
    suspend fun execute(query: String): List<Channel> = emptyList()
}