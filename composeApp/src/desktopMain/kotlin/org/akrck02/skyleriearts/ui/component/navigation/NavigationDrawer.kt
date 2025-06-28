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
import androidx.compose.material.icons.outlined.Brush
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.UploadFile
import androidx.compose.material.icons.rounded.Brush
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.CloudUpload
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.UploadFile
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.akrck02.skyleriearts.constant.CategoriesRoute
import org.akrck02.skyleriearts.constant.ImagesRoute
import org.akrck02.skyleriearts.constant.ProjectsRoute
import org.akrck02.skyleriearts.constant.Route
import org.akrck02.skyleriearts.constant.UploadRoute
import org.akrck02.skyleriearts.ui.component.input.IconButton
import org.akrck02.skyleriearts.ui.component.input.IconButtonBasicData
import org.akrck02.skyleriearts.ui.model.filter.GalleryFilter
import org.akrck02.skyleriearts.ui.model.filter.ProjectListFilter
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import org.jetbrains.compose.resources.stringResource
import skylerieartsuploader.composeapp.generated.resources.Res
import skylerieartsuploader.composeapp.generated.resources.categories
import skylerieartsuploader.composeapp.generated.resources.drawings
import skylerieartsuploader.composeapp.generated.resources.headerTitle
import skylerieartsuploader.composeapp.generated.resources.projects
import skylerieartsuploader.composeapp.generated.resources.upload


@Composable
fun NavigationDrawer(
    appViewModel: AppViewModel,
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
                modifier = Modifier.padding(0.dp).width(if (minibar) 70.dp else 310.dp)
            ) {
                Text(
                    text = stringResource(Res.string.headerTitle),
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 30.dp,
                            bottom = 30.dp
                        )
                        .fillMaxWidth(),
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary
                )

                val colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color(0xFFCFC6B4),
                    selectedBadgeColor = MaterialTheme.colors.primary
                )

                var uploadSelected = false
                var projectsSelected = false
                var categoriesSelected = false
                var imagesSelected = false

                when (appViewModel.currentRoute) {
                    UploadRoute -> uploadSelected = true
                    ProjectsRoute -> projectsSelected = true
                    CategoriesRoute -> categoriesSelected = true
                    ImagesRoute -> imagesSelected = true
                    else -> uploadSelected = true
                }

                navigationDrawerItem(
                    text = stringResource(Res.string.upload),
                    icon = Icons.Rounded.UploadFile.takeIf { uploadSelected } ?: Icons.Outlined.UploadFile,
                    contentDescription = stringResource(Res.string.upload),
                    colors = colors,
                    appViewModel = appViewModel,
                    route = UploadRoute,
                    selected = uploadSelected,
                    mini = minibar
                )

                navigationDrawerItem(
                    text = stringResource(Res.string.categories),
                    icon = Icons.Rounded.Category.takeIf { categoriesSelected } ?: Icons.Outlined.Category,
                    contentDescription = stringResource(Res.string.categories),
                    colors = colors,
                    appViewModel = appViewModel,
                    route = CategoriesRoute,
                    selected = categoriesSelected,
                    mini = minibar
                )

                navigationDrawerItem(
                    text = stringResource(Res.string.projects),
                    icon = Icons.Rounded.Palette.takeIf { projectsSelected } ?: Icons.Outlined.Palette,
                    contentDescription = stringResource(Res.string.projects),
                    colors = colors,
                    appViewModel = appViewModel,
                    route = ProjectsRoute,
                    selected = projectsSelected,
                    mini = minibar
                ) {
                    ProjectsRoute.filter = ProjectListFilter.None
                    ProjectsRoute.filterObjectId = null
                }

                navigationDrawerItem(
                    text = stringResource(Res.string.drawings),
                    icon = Icons.Rounded.Brush.takeIf { imagesSelected } ?: Icons.Outlined.Brush,
                    contentDescription = stringResource(Res.string.drawings),
                    colors = colors,
                    appViewModel = appViewModel,
                    route = ImagesRoute,
                    selected = imagesSelected,
                    mini = minibar
                ) {
                    ImagesRoute.filter = GalleryFilter.None
                    ImagesRoute.filterObjectId = null
                }

                Column(
                    modifier = Modifier.fillMaxHeight(1f).fillMaxWidth().padding(20.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        colors = ButtonDefaults.buttonColors(),
                        data = IconButtonBasicData(
                            icon = Icons.Rounded.CloudUpload,
                            description = "",
                            onClick = onSave
                        ),
                        modifier = Modifier.size(50.dp),
                        iconModifier = Modifier.size(50.dp)
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
    appViewModel: AppViewModel,
    route: Route,
    selected: Boolean = false,
    mini: Boolean = false,
    preProcess: () -> Unit = {}
) {

    NavigationDrawerItem(
        label = {

            if (mini) return@NavigationDrawerItem

            Text(
                text = text,
                fontSize = 18.sp,
                style = MaterialTheme.typography.overline,
                modifier = Modifier.padding(PaddingValues(start = 0.dp)),
                color = if (selected) Color.White else Color(0xFF9A8E75)
            )
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = if (selected) Color.White else Color(0xFF9A8E75),
                modifier = if (mini) Modifier.size(40.dp) else Modifier
            )
        },
        selected = selected,
        modifier = Modifier.padding(start = 30.dp, end = 30.dp, bottom = 5.dp)
            .height(50.dp)
            .pointerHoverIcon(PointerIcon.Hand),
        colors = colors,
        onClick = {
            preProcess()
            appViewModel.navigate(route)
        }

    )
}


