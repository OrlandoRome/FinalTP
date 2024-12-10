package com.example.wallpics.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.wallpics.models.WallpaperModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun WallpaperItem(wallpaper: WallpaperModel, onClick: (WallpaperModel) -> Unit) {
    Card(
        modifier = Modifier
            .clickable {
                onClick(wallpaper)
            },
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(wallpaper.thumbs.original),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(wallpaper.ratio.toFloat())
        )
    }
}
