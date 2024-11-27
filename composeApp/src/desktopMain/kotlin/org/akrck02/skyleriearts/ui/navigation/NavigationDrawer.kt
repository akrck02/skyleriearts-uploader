package org.akrck02.skyleriearts.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.Upload
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.akrck02.skyleriearts.navigation.GalleryRoute
import org.akrck02.skyleriearts.navigation.HomeRoute
import org.akrck02.skyleriearts.navigation.Route
import org.akrck02.skyleriearts.navigation.isCurrentRoute
import org.akrck02.skyleriearts.navigation.navigateSecurely


@Composable
fun NavigationDrawer(
    navController: NavHostController,
    mini: Boolean = false,
    content: @Composable () -> Unit,
) {

    var minibar: Boolean by remember { mutableStateOf(mini) }

    PermanentNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color(0xFFE9E5DD),
                drawerTonalElevation = 1.dp,
                modifier = Modifier.padding(0.dp).width(if (minibar) 70.dp else 280.dp)
            ) {
                Text(
                    "SkylerieArts",
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )

//                Button(onClick = { minibar = !minibar }) {
//                    Icon(imageVector = Icons.Filled.Menu, "")
//                }

                val colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color(0xFFCFC6B4),
                    selectedBadgeColor = MaterialTheme.colors.primary
                )

                navigationDrawerItem(
                    text = "Upload",
                    icon = Icons.Rounded.Upload,
                    contentDescription = "Upload",
                    colors = colors,
                    navigation = navController,
                    route = HomeRoute,
                    mini = minibar
                )

                navigationDrawerItem(
                    text = "Gallery",
                    icon = Icons.Rounded.Image,
                    contentDescription = "Gallery",
                    colors = colors,
                    navigation = navController,
                    route = GalleryRoute,
                    mini = minibar
                )
            }
        },
        content = content,
    )

}

@Composable
private fun navigationDrawerItem(
    text: String = "Title",
    icon: ImageVector = Icons.Outlined.Home,
    contentDescription: String = "",
    colors: NavigationDrawerItemColors = NavigationDrawerItemDefaults.colors(),
    navigation: NavHostController,
    route: Route,
    mini: Boolean = false
) {

    var selected: Boolean by remember { mutableStateOf(false) }
    selected = navigation.isCurrentRoute(route)

    NavigationDrawerItem(
        label = {

            if (mini) {
                return@NavigationDrawerItem
            }

            Text(
                text = text,
                modifier = Modifier.padding(PaddingValues(start = 0.dp)),
                color = if (selected) MaterialTheme.colors.primary else Color(0xFF9A8E75)
            )
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = if (selected) MaterialTheme.colors.primary else Color(0xFF9A8E75),
                modifier = if (mini) Modifier.size(40.dp) else Modifier
            )
        },
        selected = selected,
        modifier = Modifier.padding(
            start = 10.dp,
            end = 10.dp,
            bottom = 5.dp
        ).height(50.dp),
        colors = colors,
        onClick = {
            navigation.navigateSecurely(route)
        }
    )
}


