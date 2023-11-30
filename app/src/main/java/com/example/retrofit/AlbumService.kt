package com.example.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {
    @GET("/albums")
    suspend fun getAlbum():Response<Albums>

    @GET("/albums")
    suspend fun getSortedAlbum(@Query("userId") userId:Int):Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAlbums(@Path(value = "id")albumId:Int):Response<AlbumsItem>
}