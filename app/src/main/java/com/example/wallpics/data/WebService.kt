package com.example.wallpics.data

import com.example.wallpics.models.WallpaperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WebService {
    @GET("search")
    suspend fun getWallpapers(
        @Query("purity") purity: Int,
    ): Response<WallpaperResponse>


}