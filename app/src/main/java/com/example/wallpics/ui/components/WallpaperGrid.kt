package com.example.wallpics.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.wallpics.models.WallpaperModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.unit.dp

@Composable
fun WallpaperGrid (
    listaWallpappers: List<WallpaperModel>, // Replace Wallpaper with your data type
    onWallpaperClick: (WallpaperModel) -> Unit,
    modifier: Modifier = Modifier,
    loadingContent: @Composable () -> Unit = {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
) {
    if (listaWallpappers.isEmpty()) {
        loadingContent()
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(150.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalItemSpacing = 4.dp,
            modifier = modifier
        ) {
            items(listaWallpappers) { wallpaper ->
                WallpaperItem(
                    wallpaper = wallpaper,
                    onClick = { onWallpaperClick(wallpaper) }
                )
            }
        }
    }
}
