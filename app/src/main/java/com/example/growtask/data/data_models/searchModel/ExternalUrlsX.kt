package com.example.growtask.data.data_models.searchModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExternalUrlsX(
    val spotify: String? = null
) : Parcelable