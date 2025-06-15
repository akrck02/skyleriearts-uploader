package org.akrck02.skyleriearts.ui.component.route

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.akrck02.skyleriearts.navigation.AppNavigationType
import org.akrck02.skyleriearts.navigation.CategoriesRoute
import org.akrck02.skyleriearts.navigation.ImageDetailRoute
import org.akrck02.skyleriearts.navigation.ImageFullScreenRoute
import org.akrck02.skyleriearts.navigation.ImagesRoute
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.navigation.ProjectsRoute
import org.akrck02.skyleriearts.navigation.UploadRoute
import org.akrck02.skyleriearts.ui.view.categories.CategoriesView
import org.akrck02.skyleriearts.ui.view.image.ImagesView
import org.akrck02.skyleriearts.ui.view.image.detail.ImageDetailView
import org.akrck02.skyleriearts.ui.view.image.detail.ImageDetailViewModel
import org.akrck02.skyleriearts.ui.view.image.fullscreen.ImageFullScreenView
import org.akrck02.skyleriearts.ui.view.projects.ProjectsView
import org.akrck02.skyleriearts.ui.view.upload.UploadView
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.typeOf

fun NavGraphBuilder.uploadRoute(appViewModel: AppViewModel) {
    composable<UploadRoute>(
        enterTransition = { fadeIn(tween(200, 400)) },
        exitTransition = { fadeOut() }
    ) { UploadView(appViewModel) }
}

fun NavGraphBuilder.projectsRoute(appViewModel: AppViewModel) {
    composable<ProjectsRoute>(
        enterTransition = { fadeIn(tween(200, 400)) },
        exitTransition = { fadeOut() }
    ) { ProjectsView(appViewModel) }
}

fun NavGraphBuilder.categoriesRoute(appViewModel: AppViewModel) {
    composable<CategoriesRoute>(
        enterTransition = { fadeIn(tween(200, 400)) },
        exitTransition = { fadeOut() }
    ) { CategoriesView(appViewModel) }
}

fun NavGraphBuilder.imagesRoute(appViewModel: AppViewModel) {
    composable<ImagesRoute>(
        enterTransition = { fadeIn(tween(200, 400)) },
        exitTransition = { fadeOut() }
    ) { ImagesView(appViewModel) }
}

fun NavGraphBuilder.imageDetailRoute(appViewModel: AppViewModel) {
    composable<ImageDetailRoute>(
        typeMap = mapOf(typeOf<NavigationType>() to AppNavigationType),
        enterTransition = { fadeIn(tween(300, 600)) },
        exitTransition = { fadeOut() }
    ) {
        it.toRoute<ImageDetailRoute>().item
        val viewModel: ImageDetailViewModel = koinViewModel()
        viewModel.imageData = it.toRoute<ImageDetailRoute>().item.imageData
        ImageDetailView(appViewModel, viewModel)
    }
}

fun NavGraphBuilder.imageFullScreenRoute(appViewModel: AppViewModel) {
    composable<ImageFullScreenRoute>(
        typeMap = mapOf(typeOf<NavigationType>() to AppNavigationType),
        enterTransition = { fadeIn(tween(300, 600)) },
        exitTransition = { fadeOut() }
    ) {
        val data = it.toRoute<ImageFullScreenRoute>().item
        ImageFullScreenView(appViewModel, data)
    }
}