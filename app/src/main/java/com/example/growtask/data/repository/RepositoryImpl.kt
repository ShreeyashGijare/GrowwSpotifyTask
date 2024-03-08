package com.example.growtask.data.repository

import com.example.growtask.auth.AuthManager
import com.example.growtask.data.data_models.artistAlbums.ArtistsAlbum
import com.example.growtask.data.data_models.artistModel.ArtistModel
import com.example.growtask.data.data_models.artistRelated.ArtistRelated
import com.example.growtask.data.data_models.artistTopTracks.ArtistTopTracks
import com.example.growtask.data.data_models.searchModel.SearchModel
import com.example.growtask.data.data_source.SpotifyDao
import com.example.growtask.network.APIInterface

class RepositoryImpl(
    private val spotifyDao: SpotifyDao,
    private val apiInterface: APIInterface
) : Repository {


    override suspend fun searchSongs(searchQuery: String, offset: Int, limit: Int): SearchModel {
        return apiInterface.searchData(
            token = "Bearer " + AuthManager.getAccessToken()!!,
            query = searchQuery,
            offset = offset,
            limit = limit
        )
    }

    override suspend fun getArtist(id: String): ArtistModel {
        return apiInterface.getArtist(token = "Bearer " + AuthManager.getAccessToken()!!, id = id)
    }

    override suspend fun getArtistTopTracks(id: String): ArtistTopTracks {
        return apiInterface.getArtistTopTracks(
            token = "Bearer " + AuthManager.getAccessToken()!!,
            id = id
        )
    }

    override suspend fun getArtistAlbum(id: String): ArtistsAlbum {
        return apiInterface.getArtistAlbums(
            token = "Bearer " + AuthManager.getAccessToken()!!,
            id = id
        )
    }

    override suspend fun getArtistRelated(id: String): ArtistRelated {
        return apiInterface.getArtistRelated(
            token = "Bearer " + AuthManager.getAccessToken()!!,
            id = id
        )
    }

}