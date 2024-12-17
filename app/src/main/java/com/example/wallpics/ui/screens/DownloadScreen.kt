package com.example.wallpics.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wallpics.data.DownloadDao
import com.example.wallpics.models.DownloadEntity
import com.example.wallpics.models.DownloadViewModel
import com.example.wallpics.models.DownloadsViewModelFactory
import com.example.wallpics.models.toModel
import com.example.wallpics.ui.components.DetailImageItem
import com.example.wallpics.ui.theme.DarkColorScheme
import com.example.wallpics.ui.theme.LightColorScheme

@Composable
fun Download(
    downloadDao: DownloadDao,
    navController: NavController,
    onWallpaperClick: (DownloadEntity) -> Unit
) {
    val viewModel: DownloadViewModel = viewModel(
        factory = DownloadsViewModelFactory(downloadDao)
    )

    // Observar la lista de favoritos desde el ViewModel
    val downloads by viewModel.downloads.observeAsState(emptyList())

    val isDarkTheme = isSystemInDarkTheme()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tus descargas",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = if (isDarkTheme) LightColorScheme.onPrimary else DarkColorScheme.background,
                fontSize = 32.sp
            ),
            modifier = Modifier.padding(top = 16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                items(downloads) { downloadEntity ->
                    DetailImageItem(downloadEntity.toModel(), "Eliminar descarga",android.R.drawable.ic_menu_delete, onActionEvent = { viewModel.removeDownload(downloadEntity) })
                    Divider(color = if (isDarkTheme) LightColorScheme.onPrimary else DarkColorScheme.background, thickness = 1.dp)
                }
            }
        }
    }
}