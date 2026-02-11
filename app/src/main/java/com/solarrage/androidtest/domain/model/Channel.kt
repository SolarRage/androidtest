package com.solarrage.androidtest.domain.model


data class Channel(
    val id: String,
    val title: String,
    val isLive: Boolean,
    val categories: List<String>
)