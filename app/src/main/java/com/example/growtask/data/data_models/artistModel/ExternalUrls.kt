package com.example.growtask.data.data_models.artistModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExternalUrls(
    val spotify: String
) : Parcelable