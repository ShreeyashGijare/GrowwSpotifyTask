package com.example.growtask.data.data_models.searchModel

data class Playlists(
    val href: String,
    val items: List<ItemXX>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)