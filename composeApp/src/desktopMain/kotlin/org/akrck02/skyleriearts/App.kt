package org.akrck02.skyleriearts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.akrck02.skyleriearts.navigation.UploadRoute
import org.akrck02.skyleriearts.ui.component.navigation.NavigationDrawer
import org.akrck02.skyleriearts.ui.component.route.categoriesRoute
import org.akrck02.skyleriearts.ui.component.route.imageDetailRoute
import org.akrck02.skyleriearts.ui.component.route.imageFullScreenRoute
import org.akrck02.skyleriearts.ui.component.route.imagesRoute
import org.akrck02.skyleriearts.ui.component.route.projectsRoute
import org.akrck02.skyleriearts.ui.component.route.uploadRoute
import org.akrck02.skyleriearts.ui.theme.getSystemThemeColors
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(appViewModel: AppViewModel = koinViewModel()) {

    val navController: NavHostController = rememberNavController()
    appViewModel.navHostController = navController

    MaterialTheme(colors = getSystemThemeColors()) {
        NavigationDrawer(
            appViewModel = appViewModel,
            onSave = { appViewModel.uploadAndSave() }
        ) {
            NavHost(
                navController = navController,
                startDestination = UploadRoute,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
            ) {
                uploadRoute(appViewModel)
                imageDetailRoute(appViewModel)
                categoriesRoute(appViewModel)
                projectsRoute(appViewModel)
                imagesRoute(appViewModel)
                imageFullScreenRoute(appViewModel)
            }
        }
    }
}


