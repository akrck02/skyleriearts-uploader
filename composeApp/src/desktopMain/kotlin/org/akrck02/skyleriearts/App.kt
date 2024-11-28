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

    val navController: NavHostController = rememberNavController()
    val gallery: SnapshotStateMap<String, ImageData> = getCurrentGalleryFromFile()
    val imagesToShow: SnapshotStateList<ImageData> = remember { SnapshotStateList() }
    addDatabaseImages(gallery, imagesToShow)

    MaterialTheme(colors = getSystemThemeColors()) {
        NavigationDrawer(
            navController = navController,
            onSave = {

            }
        ) {
            NavHost(
                navController = navController,
                startDestination = HomeRoute,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
            ) {
                homeRoute(navController, gallery, imagesToShow)
                imageDetailRoute(navController, gallery)
                galleryRoute(navController, imagesToShow)
            }
        }
    }
}

private fun addDatabaseImages(
    gallery: SnapshotStateMap<String, ImageData>,
    imagesToShow: SnapshotStateList<ImageData>
) {
    gallery.forEach { (_, v) ->
        if (imagesToShow.contains(v).not()) {
            imagesToShow.add(v)
        }
    }
}



