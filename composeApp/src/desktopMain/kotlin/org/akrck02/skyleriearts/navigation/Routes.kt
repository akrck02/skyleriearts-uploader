package org.akrck02.skyleriearts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer
import org.akrck02.skyleriearts.viewmodel.AppViewModel

@Serializable
@SerialName("")
open class Route

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
fun Route.serialName() = this::class.serializer().descriptor.serialName

// ROUTES
@Serializable
@SerialName("/route")
data object UploadRoute : Route()


@Serializable
@SerialName("/projects")
data object ProjectsRoute : Route() {
    var filter: Int? = null
    var filterObjectId: String? = null
}

@Serializable
@SerialName("/categories")
data object CategoriesRoute : Route()

@Serializable
@SerialName("/images/detail")
data class ImageDetailRoute(
    val item: NavigationType
) : Route()

@Serializable
@SerialName("/images/view")
data class ImageFullScreenRoute(
    val item: NavigationType
) : Route()

/**
 * Check if the given route is the current one
 */
@Composable
fun NavHostController.isCurrentRoute(otherRoute: Route): Boolean {
    return otherRoute.serialName() == this.currentBackStackEntry?.destination?.route
}

/**
 * Navigate to a route skipping current one
 */
fun NavHostController.navigateSecurely(route: Route, appViewModel: AppViewModel) {

    if (route.serialName() == this.currentBackStackEntry?.destination?.route)
        return

    appViewModel.currentRoute = route
    this.navigate(route)
}

