package com.example.growtask.data.data_models.artistTopTracks

data class ArtistX(
    val external_urls: ExternalUrlsXXX,
    val followers: Followers,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String
)