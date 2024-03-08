package com.example.growtask.data.data_models.searchModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemXXX(
    val album: Album? = null,
    val artists: List<Artist>? = null,
    val available_markets: List<String>? = null,
    val disc_number: Int? = null,
    val duration_ms: Int? = null,
    val explicit: Boolean? = null,
    val external_ids: ExternalIds? = null,
    val external_urls: ExternalUrlsX? = null,
    val href: String? = null,
    val id: String? = null,
    val is_local: Boolean? = null,
    val name: String? = null,
    val popularity: Int? = null,
    val preview_url: String? = null,
    val track_number: Int? = null,
    val type: String? = null,
    val uri: String? = null
) : Parcelable