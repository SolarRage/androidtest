package com.solarrage.androidtest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ChannelDto(
    val id: String,
    val title: String,
    val category: Category,
    val isLive: Boolean
)


@Serializable
data class Category(
    val name: String,
    val subCategory: SubCategory
)

@Serializable
data class SubCategory(
    val name: String,
    val subCategory: SubCategory1,
)

@Serializable
data class SubCategory1(
    val name: String,
    )
