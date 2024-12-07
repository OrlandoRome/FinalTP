package com.example.wallpics.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WallpicsApp( modifier: Modifier = Modifier, viewModel: WallpicsViewModel = viewModel()) {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar(navController, viewModel) },
        bottomBar = { BottomNavigation(navController, viewModel) }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = Route.Home, Modifier.padding(innerPadding)) {
            composable<Route.Home> {}
            composable<Route.Favorites> {}
            composable<Route.Profile> {}
        }
    }
}
