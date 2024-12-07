package com.example.wallpics.ui

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import kotlinx.serialization.Serializable


@Serializable
sealed class Route {
    @Serializable object Home : Route()
    @Serializable object Favorites : Route()
    @Serializable object Profile : Route()
}


data class TopLevelRoute(
    val name: String,
    val route: Route,
    val icon: ImageVector,
)

val topLevelRoutes = listOf(
    TopLevelRoute("Home", Route.Home, Icons.Rounded.Home),
    TopLevelRoute("Favorites", Route.Favorites, Icons.Rounded.Favorite),
    TopLevelRoute("Profile", Route.Profile, Icons.Rounded.Person)
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, viewModel: WallpicsViewModel) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val route = topLevelRoutes.find { it.route == viewModel.currentRoute }
    LargeTopAppBar(
        title = {
            Text(
                route?.name ?: ""
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack()}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}


@Composable
fun BottomNavigation(navController: NavController, viewModel: WallpicsViewModel) {
    NavigationBar {
        topLevelRoutes.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = viewModel.currentRoute == item.route,
                onClick = {

                    viewModel.setNewRoute(item.route)
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name,
                    )
                },
            )
        }

    }
}

