package org.akrck02.skyleriearts

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.AppNavigationType
import org.akrck02.skyleriearts.navigation.GalleryRoute
import org.akrck02.skyleriearts.navigation.ImageDetailRoute
import org.akrck02.skyleriearts.navigation.ImageFullScreenRoute
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.navigation.UploadRoute
import org.akrck02.skyleriearts.ui.view.GalleryView
import org.akrck02.skyleriearts.ui.view.ImageDetailView
import org.akrck02.skyleriearts.ui.view.ImageFullScreenView
import org.akrck02.skyleriearts.ui.view.UploadView
import kotlin.reflect.typeOf


const val DEFAULT_ANIM_SPEED = 1000

fun NavGraphBuilder.uploadRoute(
    navController: NavHostController,
    gallery: SnapshotStateMap<String, ImageData>
) {
    composable<UploadRoute>(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(DEFAULT_ANIM_SPEED)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(DEFAULT_ANIM_SPEED)
            )
        }
    ) {
        UploadView(navController, gallery)
    }
}

fun NavGraphBuilder.galleryRoute(
    navController: NavHostController,
    gallery: SnapshotStateMap<String, ImageData>
) {
    composable<GalleryRoute>(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(DEFAULT_ANIM_SPEED)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(DEFAULT_ANIM_SPEED)
            )
        }
    ) {
        GalleryView(navController, gallery)
    }
}


fun NavGraphBuilder.imageDetailRoute(
    navController: NavHostController,
    gallery: SnapshotStateMap<String, ImageData>
) {
    composable<ImageDetailRoute>(
        typeMap = mapOf(typeOf<NavigationType>() to AppNavigationType),
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300,
                    600
                )
            )
        },
        exitTransition = {
            fadeOut()
        }
    ) {
        val data = it.toRoute<ImageDetailRoute>().item
        ImageDetailView(navController = navController, data = data, gallery = gallery)
    }
}

fun NavGraphBuilder.imageFullScreenRoute(
    navController: NavHostController,
    gallery: SnapshotStateMap<String, ImageData>
) {
    composable<ImageFullScreenRoute>(
        typeMap = mapOf(typeOf<NavigationType>() to AppNavigationType),
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300,
                    600
                )
            )
        },
        exitTransition = {
            fadeOut()
        }
    ) {
        val data = it.toRoute<ImageFullScreenRoute>().item
        ImageFullScreenView(navController = navController, data = data, gallery = gallery)
    }

}