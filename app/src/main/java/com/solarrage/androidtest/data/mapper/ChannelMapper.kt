package com.solarrage.androidtest.data.mapper

import com.solarrage.androidtest.data.model.ChannelDto
import com.solarrage.androidtest.domain.model.Channel

class ChannelMapper {

    fun map(dto: ChannelDto): Channel {
        return Channel(
            id = dto.id,
            title = dto.title,
            category = dto.category,
            isLive = dto.isLive
        )
    }
}