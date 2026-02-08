package com.solarrage.androidtest.domain.repository

import com.solarrage.androidtest.domain.model.Channel

interface ChannelRepository {
    suspend fun getChannels(): List<Channel>
}