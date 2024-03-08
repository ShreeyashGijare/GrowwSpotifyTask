package com.example.growtask.data.data_models.searchModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val album_type: String? = null,
    val artists: List<Artist>? = null,
    val available_markets: List<String>? = null,
    val external_urls: ExternalUrlsX? = null,
    val href: String? = null,
    val id: String? = null,
    val images: List<Image>? = null,
    val name: String? = null,
    val release_date: String? = null,
    val release_date_precision: String? = null,
    val total_tracks: Int? = null,
    val type: String? = null,
    val uri: String? = null
): Parcelable