package com.solarrage.androidtest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ChannelDto(
    val id: String,
    val title: String,
    val category: String,
    val isLive: Boolean
)