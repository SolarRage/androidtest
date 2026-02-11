package com.solarrage.androidtest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ChannelDto(
    val id: String,
    val title: String,
    val isLive: Boolean,
    val category: CategoryDto
)

@Serializable
data class CategoryDto(
    val name: String,
    val subCategory: CategoryDto? = null
)