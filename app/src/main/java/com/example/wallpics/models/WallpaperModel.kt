package com.example.wallpics.models

import com.google.gson.annotations.SerializedName

data class WallpaperModel (
    @SerializedName("id")
    var id:String,
    @SerializedName("url")
    var url:String,
    @SerializedName("short_url")
    var short_url:String,
    @SerializedName("category")
    var category:String,
    @SerializedName("file_size")
    var file_size:String,
    @SerializedName("file_type")
    var file_type: String,
    @SerializedName("purity")
    var purity: String,
    var created_at:String,
    var resolution:String,
    var favorites:String,
    @SerializedName("thumbs")
    var thumbs: Thumbs
)

data class Thumbs(
    @SerializedName("large")
    var large: String, // Miniatura grande
    @SerializedName("original")
    var original: String, // Imagen original
    @SerializedName("small")
    var small: String // Miniatura pequeña
)