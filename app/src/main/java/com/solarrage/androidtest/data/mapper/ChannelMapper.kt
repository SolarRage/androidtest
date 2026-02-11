package com.solarrage.androidtest.data.mapper

import com.solarrage.androidtest.data.model.CategoryDto
import com.solarrage.androidtest.data.model.ChannelDto
import com.solarrage.androidtest.domain.model.Channel

class ChannelMapper {

    fun map(dto: ChannelDto): Channel {
        return Channel(
            id = dto.id,
            title = dto.title,
            isLive = dto.isLive,
            categories = collectCategories(dto.category)
        )
    }

    private fun collectCategories(categories: CategoryDto): List<String> {
        val result = mutableListOf<String>()
        var current: CategoryDto? = categories
        while (current != null) {
            result += current.name
            current = current.subCategory
        }

        return result
    }
}