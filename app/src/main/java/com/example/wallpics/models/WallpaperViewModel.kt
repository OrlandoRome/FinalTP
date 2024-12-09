package com.example.wallpics.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpics.data.retrofitService
import kotlinx.coroutines.launch

class WallpaperViewModel: ViewModel() {

    var imageList = mutableStateOf<List<WallpaperModel>>(emptyList()) // La lista de wallpapers
        private set
    var selectedWallpaper: WallpaperModel? = null
        private set

    // Función para obtener los wallpapers desde la API
    fun getWallpapers(purity: Int) {
        viewModelScope.launch {
            try {
                val response = retrofitService.webService.getWallpapers(purity)
                if (response.isSuccessful) {
                    // Deserializamos el cuerpo de la respuesta como WallpaperResponse
                    val wallpapersResponse = response.body() // Esto es de tipo WallpaperResponse
                    println("Respuesta de la API: $wallpapersResponse")
                    if (wallpapersResponse != null) {
                        imageList.value = wallpapersResponse.resultados // Asigna los wallpapers a la lista
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