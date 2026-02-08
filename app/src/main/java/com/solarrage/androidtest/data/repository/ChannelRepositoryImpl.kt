package com.solarrage.androidtest.data.repository

import com.solarrage.androidtest.data.mapper.ChannelMapper
import com.solarrage.androidtest.data.source.ChannelLocalDataSource
import com.solarrage.androidtest.domain.model.Channel
import com.solarrage.androidtest.domain.repository.ChannelRepository

class ChannelRepositoryImpl(
    private val localDataSource: ChannelLocalDataSource,
    private val mapper: ChannelMapper
) : ChannelRepository {

    override suspend fun getChannels(): List<Channel> {
        return localDataSource
            .loadChannels()
            .map(mapper::map)
    }
}