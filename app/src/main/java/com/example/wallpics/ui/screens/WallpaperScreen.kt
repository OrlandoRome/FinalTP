package com.example.wallpics.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.wallpics.models.WallpaperModel
import com.example.wallpics.models.WallpaperViewModel
import com.example.wallpics.ui.Route
import com.example.wallpics.ui.theme.BarraFondoDark
import com.example.wallpics.ui.theme.DarkColorScheme
import com.example.wallpics.ui.theme.LightColorScheme

@Composable
fun WallpaperScreen(
    wallpaperViewModel: WallpaperViewModel = viewModel(),
    navController: NavController
) {
    val listaWallpappers = wallpaperViewModel.imageList.value
    val isDarkTheme = isSystemInDarkTheme()
    // función para obtener los wallpapers
    LaunchedEffect(Unit) {
        wallpaperViewModel.getWallpapers(purity = 100)
    }

    // Pantalla principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp), // Añadir padding alrededor
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Card(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .wrapContentSize() // Ajusta el tamaño de la tarjeta al contenido
                .padding(horizontal = 30.dp), // Ajusta el padding según sea necesario
            shape = RoundedCornerShape(50), // Hace que la tarjeta sea ovalada
            colors = CardDefaults.cardColors(
                containerColor = if (isDarkTheme) DarkColorScheme.primary else BarraFondoDark
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp)
        ) {
            Text(
                text = "Úlimos añadidos",
                modifier = Modifier
                    .padding(10.dp),
                fontWeight = FontWeight.SemiBold,
                color = if (isDarkTheme) LightColorScheme.onPrimary else DarkColorScheme.background,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        // Contenido principal
        if (listaWallpappers.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(300.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalItemSpacing = 12.dp,
                modifier = Modifier.fillMaxSize()
            ) {
                items(listaWallpappers) { wallpaper ->
                    WallpaperItem(
                        wallpaper = wallpaper,
                        onClick = {
                            wallpaperViewModel.selectWallpaper(wallpaper)
                            navController.navigate(Route.WallpaperView)
                        },
                    )
                }
            }
        }
    }
}

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
