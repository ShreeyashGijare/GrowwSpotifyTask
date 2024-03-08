package com.example.growtask.data.data_models.dummy_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DummyModel(
    @PrimaryKey(autoGenerate = false)
    val name: String = ""
)
