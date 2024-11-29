package org.akrck02.skyleriearts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.serialization.Serializable
import java.util.Objects

@Serializable
open class Route

var current: Route? = null

// ROUTES
@Serializable
data object UploadRoute : Route()

@Serializable
data object GalleryRoute : Route()

@Serializable
data class ImageDetailRoute(
    val item: NavigationType
) : Route()

@Serializable
data class ImageFullScreenRoute(
    val item: NavigationType
) : Route()


@Composable
fun NavHostController.getCurrentRoute(): String? {
    return this.currentBackStackEntryAsState().value?.destination?.route
}

/**
 * Check if the given route is the current one
 */
@Composable
fun NavHostController.isCurrentRoute(otherRoute: Route): Boolean {
    val currentRoute = this.getCurrentRoute()?.trim()
    val givenRoutePackage = getRoutePackageName(otherRoute).trim()
    return Objects.equals(currentRoute, givenRoutePackage)
}

/**
 * Navigate to a route skipping current one
 */
fun NavHostController.navigateSecurely(route: Route) {

    val currentRoutePackage = current?.let { getRoutePackageName(it) }

    if (Objects.equals(getRoutePackageName(route), currentRoutePackage))
        return

    current = route
    this.navigate(route)
}


/**
 * Get the package name of a Route object
 */
fun getRoutePackageName(route: Route): String {
    return route.javaClass.kotlin.qualifiedName.toString()
}
