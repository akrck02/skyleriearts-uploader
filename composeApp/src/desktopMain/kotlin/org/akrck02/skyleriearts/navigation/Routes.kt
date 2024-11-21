package org.akrck02.skyleriearts.navigation

import kotlinx.serialization.Serializable

//ROUTES
@Serializable
data object HomeRoute

@Serializable
data class ImageDetailRoute(
    val item: NavigationType
)