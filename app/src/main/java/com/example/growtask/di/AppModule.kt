package com.example.growtask.di

import android.app.Application
import androidx.room.Room
import com.example.growtask.data.data_source.SpotifyDatabase
import com.example.growtask.data.repository.Repository
import com.example.growtask.data.repository.RepositoryImpl
import com.example.growtask.network.APIClient
import com.example.growtask.network.APIInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): SpotifyDatabase {
        return Room.databaseBuilder(
            app,
            SpotifyDatabase::class.java,
            SpotifyDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesSpotifyDatabase(db: SpotifyDatabase, apiInterface: APIInterface): Repository {
        return RepositoryImpl(db.spotifyDao, apiInterface)
    }

    @Provides
    @Singleton
    fun providesAPIInterface(): APIInterface =
        APIClient().getClient()!!.create(APIInterface::class.java)

}