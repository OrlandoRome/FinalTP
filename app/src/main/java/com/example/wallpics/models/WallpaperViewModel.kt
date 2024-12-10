package com.example.wallpics.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wallpics.data.retrofitService
import kotlinx.coroutines.launch

class WallpaperViewModel: ViewModel() {

    var imageList = mutableStateOf<List<WallpaperModel>>(emptyList()) // La lista de wallpapers
        private set
    var selectedWallpaper: WallpaperModel? = null
        private set
    var currentPage: Int=  1
    var lastPage: Int= 1

    // Función para obtener los wallpapers desde la API
    fun getWallpapers(purity: Int, page: Int) {
        viewModelScope.launch {
            try {
                val response = retrofitService.webService.getWallpapers(purity, page)
                Log.d("response-data", response.toString())
                if (response.isSuccessful) {
                    // Deserializamos el cuerpo de la respuesta como WallpaperResponse
                    val wallpapersResponse = response.body() // Esto es de tipo WallpaperResponse
                    println("Respuesta de la API: $wallpapersResponse")
                    if (wallpapersResponse != null) {
                        imageList.value += wallpapersResponse.resultados
                        currentPage = wallpapersResponse.meta.currentPage
                        lastPage = wallpapersResponse.meta.lastPage
                    } else {
                        println("La respuesta está vacía o nula.")
                    }
                } else {
                    println("Error en la respuesta: ${response.message()}")
                }
            } catch (e: Exception) {
                println("Error al cargar las imágenes: ${e.message}")
            }
        }
    }

    fun selectWallpaper (wallpaper: WallpaperModel) {
        selectedWallpaper = wallpaper
    }

}