package com.solarrage.androidtest.data.source

import com.solarrage.androidtest.data.model.ChannelDto

interface ChannelLocalDataSource {
    suspend fun loadChannels(): List<ChannelDto>
}