package org.akrck02.skyleriearts.ui.component.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.UploadFile
import androidx.compose.material.icons.rounded.Save
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.gallery
import kotlinproject.composeapp.generated.resources.headerTitle
import kotlinproject.composeapp.generated.resources.upload
import org.akrck02.skyleriearts.navigation.GalleryRoute
import org.akrck02.skyleriearts.navigation.Route
import org.akrck02.skyleriearts.navigation.UploadRoute
import org.akrck02.skyleriearts.navigation.isCurrentRoute
import org.akrck02.skyleriearts.navigation.navigateSecurely
import org.akrck02.skyleriearts.ui.component.input.IconButton
import org.akrck02.skyleriearts.ui.component.input.IconButtonBasicData
import org.jetbrains.compose.resources.stringResource


@Composable
fun NavigationDrawer(
    navController: NavHostController,
    mini: Boolean = false,
    onSave: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    val minibar: Boolean by remember { mutableStateOf(mini) }

    PermanentNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color(0xFFE9E5DD),
                drawerTonalElevation = 1.dp,
                modifier = Modifier.padding(0.dp).width(if (minibar) 70.dp else 280.dp)
            ) {
                Text(
                    text = stringResource(Res.string.headerTitle),
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 1.25.em,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary
                )

                val colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color(0xFFCFC6B4),
                    selectedBadgeColor = MaterialTheme.colors.primary
                )

                navigationDrawerItem(
                    text = stringResource(Res.string.upload),
                    icon = Icons.Outlined.UploadFile,
                    contentDescription = "Upload",
                    colors = colors,
                    navigation = navController,
                    route = UploadRoute,
                    mini = minibar
                )

                navigationDrawerItem(
                    text = stringResource(Res.string.gallery),
                    icon = Icons.Outlined.Image,
                    contentDescription = "Gallery",
                    colors = colors,
                    navigation = navController,
                    route = GalleryRoute,
                    mini = minibar
                )

                Column(
                    modifier = Modifier.fillMaxHeight(1f).fillMaxWidth().padding(20.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        colors = ButtonDefaults.buttonColors(),
                        data = IconButtonBasicData(
                            icon = Icons.Rounded.Save,
                            description = "",
                            onClick = onSave
                        ),
                        modifier = Modifier.size(50.dp)
                    )
                }

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


