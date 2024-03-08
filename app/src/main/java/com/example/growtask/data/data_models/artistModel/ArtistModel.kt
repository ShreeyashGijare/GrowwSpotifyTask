package com.example.growtask.data.data_models.artistModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistModel(
    val external_urls: ExternalUrls,
    val followers: Followers,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String
) : Parcelable