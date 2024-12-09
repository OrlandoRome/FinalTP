package com.example.wallpics.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.wallpics.constans.constantes
import com.example.wallpics.models.WallpaperModel
import com.example.wallpics.models.WallpaperViewModel
import com.example.wallpics.R

@Composable
fun WallpaperScreen(wallpaperViewModel: WallpaperViewModel = viewModel(), innerPadding: PaddingValues){
    val listaWallpappers = wallpaperViewModel.imageList.value

    // función para obtener los wallpapers
    LaunchedEffect(Unit) {
        wallpaperViewModel.getWallpapers(purity = 100)
    }

    // Pantalla principal
    Column (
        modifier =  Modifier
            .fillMaxSize()
            .padding(0.dp), // Añadir padding alrededor
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .wrapContentSize() // Ajusta el tamaño de la tarjeta al contenido
                .padding(horizontal = 30.dp), // Ajusta el padding según sea necesario
            shape = RoundedCornerShape(50), // Hace que la tarjeta sea ovalada
            elevation = CardDefaults.elevatedCardElevation(4.dp)
        ) {
            Text(
                text = "Úlimos añadidos",
                modifier = Modifier
                    .padding(10.dp),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold,
                style =  MaterialTheme.typography.bodyMedium,
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Dos columnas
                contentPadding = PaddingValues(8.dp) // Espaciado alrededor de la grilla
            ) {
                items(listaWallpappers) { wallpaper ->
                    WallpaperItem(wallpaper = wallpaper) { clickedWallpaper ->
                        // Acción al hacer clic en una imagen
                        println("Wallpaper clickeado: ${clickedWallpaper.category}")
                    }
                }
            }
        }
    }
}

@Composable
fun WallpaperItem(wallpaper: WallpaperModel, onClick: (WallpaperModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onClick(wallpaper)
                       }, // Hace que la tarjeta sea clickeable
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(wallpaper.thumbs.large),
            contentDescription = wallpaper.category,
            modifier = Modifier
                .aspectRatio(1f) // Mantiene la relación de aspecto
                .fillMaxWidth() // Llenatodo el ancho
                .padding(0.dp), // Elimina cualquier relleno adicional
            contentScale = ContentScale.Crop
        )
    }
}
