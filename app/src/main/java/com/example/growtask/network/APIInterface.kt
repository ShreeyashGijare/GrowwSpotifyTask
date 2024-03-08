package com.example.growtask.network

import com.example.growtask.data.data_models.artistAlbums.ArtistsAlbum
import com.example.growtask.data.data_models.artistModel.ArtistModel
import com.example.growtask.data.data_models.artistRelated.ArtistRelated
import com.example.growtask.data.data_models.artistTopTracks.ArtistTopTracks
import com.example.growtask.data.data_models.searchModel.SearchModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("search")
    suspend fun searchData(
        @Header("Authorization") token: String,
        @Query("q") query: String,
        @Query("type") type: List<String> = listOf("track", "album", "artist", "playlist"),
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): SearchModel

    @GET("artists/{id}")
    suspend fun getArtist(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): ArtistModel

    @GET("artists/{id}/top-tracks")
    suspend fun getArtistTopTracks(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): ArtistTopTracks

    @GET("artists/{id}/albums")
    suspend fun getArtistAlbums(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): ArtistsAlbum

    @GET("artists/{id}/related-artists")
    suspend fun getArtistRelated(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): ArtistRelated

}