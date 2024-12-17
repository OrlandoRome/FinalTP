package com.example.wallpics.models

import com.google.gson.Gson

fun WallpaperEntity.toModel(): WallpaperModel {
    val gson = Gson()
    return WallpaperModel(
        id = this.id,
        url = "",
        shortUrl = this.shortUrl,
        category = this.category,
        fileSize = "",
        fileType = "",
        purity = "",
        createdAt = "",
        resolution = this.resolution,
        favorites = "",
        thumbs = gson.fromJson(this.thumbs, Thumbs::class.java),
        ratio = this.ratio,
        dimensionX = 0,
        dimensionY = 0,
        path = this.path
    )
}

fun DownloadEntity.toModel(): WallpaperModel {
    val gson = Gson()
    return WallpaperModel(
        id = this.id,
        url = "",
        shortUrl = this.shortUrl,
        category = this.category,
        fileSize = this.fileSize,
        fileType = "",
        purity = "",
        createdAt = "",
        resolution = this.resolution,
        favorites = "",
        thumbs = gson.fromJson(this.thumbs, Thumbs::class.java),
        ratio = this.ratio,
        dimensionX = 0,
        dimensionY = 0,
        path = this.path
    )
}