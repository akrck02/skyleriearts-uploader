package org.akrck02.skyleriearts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.akrck02.skyleriearts.core.getCurrentGalleryFromFile
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.HomeRoute
import org.akrck02.skyleriearts.ui.navigation.NavigationDrawer
import org.akrck02.skyleriearts.ui.theme.getSystemThemeColors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val gallery: SnapshotStateMap<String, ImageData> = getCurrentGalleryFromFile()
    val imagesToUpload: SnapshotStateList<ImageData> = remember { SnapshotStateList() }
    val navController: NavHostController = rememberNavController()

    gallery.forEach { (_, v) ->
        imagesToUpload.add(v)
    }

    MaterialTheme(colors = getSystemThemeColors()) {
        NavigationDrawer(navController = navController) {
            NavHost(
                navController = navController,
                startDestination = HomeRoute,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
            ) {
                homeRoute(gallery, imagesToUpload)
                imageDetailRoute(navController, gallery)
                galleryRoute(navController, imagesToUpload)
            }
        }
    }
}



