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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.akrck02.skyleriearts.core.getCurrentGalleryFromFile
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.AppNavigationType
import org.akrck02.skyleriearts.navigation.HomeRoute
import org.akrck02.skyleriearts.navigation.ImageDetailRoute
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.ui.theme.getSystemThemeColors
import org.akrck02.skyleriearts.ui.view.HomeView
import org.akrck02.skyleriearts.ui.view.ImageDetailView
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.reflect.typeOf

@Composable
@Preview
fun App() {

    val gallery: SnapshotStateMap<String, ImageData> = getCurrentGalleryFromFile()
    val imagesToUpload: SnapshotStateList<ImageData> = remember { SnapshotStateList() }
    val navController: NavHostController = rememberNavController()

    MaterialTheme(colors = getSystemThemeColors()) {
        NavHost(
            navController = navController,
            startDestination = HomeRoute,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            homeRoute(navController, gallery, imagesToUpload)
            imageDetailRoute(navController, gallery)
        }
    }


}

fun NavGraphBuilder.homeRoute(
    navController: NavHostController,
    gallery: SnapshotStateMap<String, ImageData>,
    imagesToUpload: SnapshotStateList<ImageData>
) {
    composable<HomeRoute> {
        HomeView(navController, gallery, imagesToUpload)
    }
}

fun NavGraphBuilder.imageDetailRoute(
    navController: NavHostController,
    gallery: SnapshotStateMap<String, ImageData>
) {
    composable<ImageDetailRoute>(typeMap = mapOf(typeOf<NavigationType>() to AppNavigationType)) {
        val data = it.toRoute<ImageDetailRoute>().item
        ImageDetailView(navController = navController, data = data, gallery = gallery)
    }
}