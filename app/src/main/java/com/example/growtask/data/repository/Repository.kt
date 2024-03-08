package com.example.growtask.data.repository

import com.example.growtask.data.data_models.artistAlbums.ArtistsAlbum
import com.example.growtask.data.data_models.artistModel.ArtistModel
import com.example.growtask.data.data_models.artistRelated.ArtistRelated
import com.example.growtask.data.data_models.artistTopTracks.ArtistTopTracks
import com.example.growtask.data.data_models.searchModel.SearchModel

interface Repository {

    suspend fun searchSongs(searchQuery: String, offset: Int, limit: Int): SearchModel

    suspend fun getArtist(id: String): ArtistModel

    suspend fun getArtistTopTracks(id: String): ArtistTopTracks

    suspend fun getArtistAlbum(id: String): ArtistsAlbum

    suspend fun getArtistRelated(id: String) : ArtistRelated
}