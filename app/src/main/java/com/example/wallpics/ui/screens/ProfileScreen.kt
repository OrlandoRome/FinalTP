package com.example.wallpics.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wallpics.models.WallpaperViewModel


@Composable
fun ProfileScreen(
    wallpaperViewModel: WallpaperViewModel = viewModel(),
    navController: NavController
) {
    val isDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "PikMe",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontSize = 32.sp
                ),
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Contenido",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.LightGray
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Opción "Subidas"
            MenuItem(
                iconRes = android.R.drawable.arrow_up_float,
                title = "Subidas",
                subtitle = "Subir contenido y ver tus subidas"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Opción "Descargas"
            MenuItem(
                iconRes = android.R.drawable.arrow_down_float,
                title = "Descargas",
                subtitle = ""
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Opción "Cambiar de tema"
            MenuItem(
                iconRes = android.R.drawable.ic_menu_manage,
                title = "Cambiar de tema",
                subtitle = ""
            )

            Spacer(modifier = Modifier.height(16.dp))

            Divider(color = Color.Gray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(16.dp))

            // Opción "Iniciar sesión"
            MenuItem(
                iconRes = android.R.drawable.ic_menu_revert,
                title = "Iniciar sesión",
                subtitle = ""
            )
        }
    }
}

@Composable
fun MenuItem(iconRes: Int, title: String, subtitle: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontSize = 18.sp
                )
            )
            if (subtitle.isNotEmpty()) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}