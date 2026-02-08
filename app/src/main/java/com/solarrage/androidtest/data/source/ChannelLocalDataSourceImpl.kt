package com.solarrage.androidtest.data.source

import android.content.Context
import com.solarrage.androidtest.data.model.ChannelDto

class ChannelLocalDataSourceImpl(
    private val context: Context
) : ChannelLocalDataSource {
    override suspend fun loadChannels(): List<ChannelDto> {
        TODO("Not yet implemented")
    }
}