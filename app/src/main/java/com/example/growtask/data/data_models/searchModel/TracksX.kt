package com.example.growtask.data.data_models.searchModel

data class TracksX(
    val href: String,
    val items: List<ItemXXX>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)