package org.akrck02.skyleriearts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.akrck02.skyleriearts.ui.view.ImageDetailView
import org.akrck02.skyleriearts.ui.view.MainView
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.name,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        composable(route = Screen.Home.name) {
            MainView(onImageClick = { imageData ->
                navController.navigate(
                    route = "/images/details/${imageData.name}",
                )
            })
        }
        composable(route = Screen.ImageDetail.name) { backStackEntry ->
            val imageName = backStackEntry.arguments?.getString("imageId")
            ImageDetailView(imageName!!)
        }

    }
}