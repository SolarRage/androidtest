package com.solarrage.androidtest.data.mapper

import com.solarrage.androidtest.data.model.ChannelDto
import com.solarrage.androidtest.domain.model.Category
import com.solarrage.androidtest.domain.model.Channel
import com.solarrage.androidtest.domain.model.SubCategory
import com.solarrage.androidtest.domain.model.SubCategory1

class ChannelMapper {

    fun map(dto: ChannelDto): Channel {
        val channel = Channel(
            id = dto.id,
            title = dto.title,
            category = Category(
                name = dto.category.name,
                subCategory = SubCategory(
                    name = dto.category.subCategory.name,
                    subCategory = SubCategory1(
                        dto.category.subCategory.subCategory.name,
                    )
                )
            ),
            isLive = dto.isLive
        )
        return channel
    }
}