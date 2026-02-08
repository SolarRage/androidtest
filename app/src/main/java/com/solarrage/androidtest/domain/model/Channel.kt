package com.solarrage.androidtest.domain.model

data class Channel(
    val id: String,
    val title: String,
    val category: String,
    val isLive: Boolean
)