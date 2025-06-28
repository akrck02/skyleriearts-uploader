package org.akrck02.skyleriearts.constant

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer
import org.akrck02.skyleriearts.ui.model.GalleryImage
import org.akrck02.skyleriearts.ui.model.filter.GalleryFilter
import org.akrck02.skyleriearts.ui.model.filter.ProjectListFilter
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
    var filter: ProjectListFilter = ProjectListFilter.None
    var filterObjectId: String? = null
}

@Serializable
@SerialName("/categories")
data object CategoriesRoute : Route()


@Serializable
@SerialName("/images")
data object ImagesRoute : Route() {
    var filter: GalleryFilter = GalleryFilter.None
    var filterObjectId: String? = null
}


@Serializable
@SerialName("/images/detail")
object ImageDetailRoute : Route() {
    var image: GalleryImage? = null
}

@Serializable
@SerialName("/images/view")
object ImageFullScreenRoute : Route() {
    var image: GalleryImage? = null
}

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

