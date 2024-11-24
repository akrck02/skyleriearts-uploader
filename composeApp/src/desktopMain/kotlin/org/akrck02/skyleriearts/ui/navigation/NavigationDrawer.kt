package org.akrck02.skyleriearts.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Image
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.akrck02.skyleriearts.navigation.GalleryRoute
import org.akrck02.skyleriearts.navigation.HomeRoute


@Composable
fun NavigationDrawer(
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    PermanentNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color(0xFFE9E5DD),
                drawerTonalElevation = 1.dp,
                modifier = Modifier.padding(0.dp)
            ) {
                Text(
                    "SkylerieArts",
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )

                val colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color(0xFFCFC6B4),
                    selectedBadgeColor = MaterialTheme.colors.primary
                )

                navigationDrawerItem(
                    text = "Home",
                    icon = Icons.Outlined.Home,
                    contentDescription = "Home",
                    selected = true,
                    colors = colors,
                    navigation = navController,
                    route = HomeRoute
                )

                navigationDrawerItem(
                    text = "Gallery",
                    icon = Icons.Outlined.Image,
                    contentDescription = "Gallery",
                    selected = false,
                    colors = colors,
                    navigation = navController,
                    route = GalleryRoute
                )
            }
        },
        content = content,
    )

}

@Composable
private fun navigationDrawerItem(
    text: String = "",
    icon: ImageVector,
    contentDescription: String = "",
    selected: Boolean = false,
    colors: NavigationDrawerItemColors = NavigationDrawerItemDefaults.colors(),
    navigation: NavHostController,
    route: Any
) {

    var selectedBool = selected
    var selectedValue: Boolean by remember { mutableStateOf(selectedBool) }

    NavigationDrawerItem(
        label = {
            Text(
                text = text,
                modifier = Modifier.padding(PaddingValues(start = 0.dp)),
                color = if (selectedValue) MaterialTheme.colors.primary else Color(0xFF9A8E75)
            )
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = if (selectedValue) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary
            )
        },
        selected = selectedValue,
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp).height(50.dp),
        colors = colors,
        onClick = {
            selectedBool = true
            navigation.navigate(route)
        }
    )
}

@Composable
fun currentRoute(navController: NavController): Any? =
    navController.currentBackStackEntryAsState().value?.destination?.route