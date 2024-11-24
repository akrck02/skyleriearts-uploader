package org.akrck02.skyleriearts

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.AppNavigationType
import org.akrck02.skyleriearts.navigation.GalleryRoute
import org.akrck02.skyleriearts.navigation.HomeRoute
import org.akrck02.skyleriearts.navigation.ImageDetailRoute
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.ui.view.GalleryView
import org.akrck02.skyleriearts.ui.view.HomeView
import org.akrck02.skyleriearts.ui.view.ImageDetailView
import kotlin.reflect.typeOf


const val DEFAULT_ANIM_SPEED = 1000

fun NavGraphBuilder.homeRoute(
    navController: NavHostController,
    gallery: SnapshotStateMap<String, ImageData>,
    imagesToShow: SnapshotStateList<ImageData>
) {
    composable<HomeRoute>(
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
        HomeView(navController, gallery, imagesToShow)
    }
}

fun NavGraphBuilder.galleryRoute(
    navController: NavHostController,
    imagesToUpload: SnapshotStateList<ImageData>
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
        GalleryView(navController, imagesToUpload)
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
                    400
                )
            )
        },
        exitTransition = {
            fadeOut(

            )
        }
    ) {
        val data = it.toRoute<ImageDetailRoute>().item
        ImageDetailView(navController = navController, data = data, gallery = gallery)
    }
}