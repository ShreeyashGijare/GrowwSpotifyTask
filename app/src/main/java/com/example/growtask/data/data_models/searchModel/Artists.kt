package com.example.growtask.data.data_models.searchModel

data class Artists(
    val href: String,
    val items: List<ItemX>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)