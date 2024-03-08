package com.example.growtask.data.data_models.searchModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExternalIds(
    val isrc: String? = null
) : Parcelable