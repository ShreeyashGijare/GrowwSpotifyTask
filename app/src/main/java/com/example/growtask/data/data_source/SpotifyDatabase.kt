package com.example.growtask.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.growtask.data.data_models.dummy_model.DummyModel


@Database(
    entities = [DummyModel::class],
    version = 1,
    exportSchema = false
)
abstract class SpotifyDatabase : RoomDatabase() {

    abstract val spotifyDao: SpotifyDao

    companion object {
        const val DATABASE_NAME = "spotify_db"
    }

}