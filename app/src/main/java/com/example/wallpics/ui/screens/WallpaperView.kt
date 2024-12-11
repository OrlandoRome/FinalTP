package com.example.wallpics.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wallpics.models.WallpaperViewModel
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperView(wallpaperViewModel: WallpaperViewModel = viewModel(), scrollBehavior: TopAppBarScrollBehavior) {
    scrollBehavior.state.heightOffset = 0f
    val picture = wallpaperViewModel.selectedWallpaper!!
        Image(
            painter = rememberAsyncImagePainter(picture.path),
            contentDescription = picture.category,
            modifier = Modifier
                .fillMaxSize()
            ,
        )
}