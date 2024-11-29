package org.akrck02.skyleriearts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.akrck02.skyleriearts.core.saveGalleryToFile
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.UploadRoute
import org.akrck02.skyleriearts.ui.navigation.NavigationDrawer
import org.akrck02.skyleriearts.ui.theme.getSystemThemeColors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(gallery: SnapshotStateMap<String, ImageData>) {

    val navController: NavHostController = rememberNavController()
    MaterialTheme(colors = getSystemThemeColors()) {
        NavigationDrawer(
            navController = navController,
            onSave = { saveGalleryToFile(gallery) }
        ) {
            NavHost(
                navController = navController,
                startDestination = UploadRoute,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
            ) {
                uploadRoute(navController, gallery)
                imageDetailRoute(navController, gallery)
                galleryRoute(navController, gallery)
                imageFullScreenRoute(navController, gallery)
            }
        }
    }
}


