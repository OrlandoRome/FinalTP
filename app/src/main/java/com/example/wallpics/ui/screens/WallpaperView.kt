package com.example.wallpics.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wallpics.models.WallpaperViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.wallpics.ui.components.ExpandableFAB
import com.example.wallpics.ui.components.FABItem
import com.example.wallpics.utils.AndroidDownloader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperView(
    wallpaperViewModel: WallpaperViewModel = viewModel(),
    scrollBehavior: TopAppBarScrollBehavior
) {
    val context = LocalContext.current
    val downloader = remember { AndroidDownloader(context) }

    scrollBehavior.state.heightOffset = 0f
    val picture = wallpaperViewModel.selectedWallpaper!!

    Scaffold(
        floatingActionButton = {
            val itemList = listOf(
                FABItem(icon = Icons.Rounded.Favorite, text = "Add to Favorites"),
                FABItem(icon = Icons.Rounded.Create, text = "Set Wallpaper"),
                FABItem(icon = Icons.Rounded.ArrowDropDown, text = "Download"),
            )
            ExpandableFAB(
                items = itemList,
                onItemClick = {item ->
                    when(item.text) {
                        "Download" -> {
                            downloader.downloadFile(picture.path, picture.id)
                        }
                        "Set Wallpaper" -> {}
                    }
                }
            )
        }
    ) { innerPadding ->
        Image(
            painter = rememberAsyncImagePainter(picture.path),
            contentDescription = picture.category,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
    }
}