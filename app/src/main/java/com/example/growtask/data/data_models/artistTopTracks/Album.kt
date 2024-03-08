package com.example.growtask.data.data_models.artistTopTracks

data class Album(
    val album_type: String,
    val artists: List<Artist>,
    val available_markets: List<String>,
    val external_urls: ExternalUrlsXXX,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val restrictions: RestrictionsX,
    val total_tracks: Int,
    val type: String,
    val uri: String
)