package com.solarrage.androidtest.domain.model


data class Channel(
    val id: String,
    val title: String,
    val category: Category,
    val isLive: Boolean
)



data class Category(
    val name: String,
    val subCategory: SubCategory
)


data class SubCategory(
    val name: String,
    val subCategory: SubCategory1,
)


data class SubCategory1(
    val name: String,
)
