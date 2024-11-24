package org.akrck02.skyleriearts.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.BackgroundOpacity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import org.akrck02.skyleriearts.core.saveGalleryToFile
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.GalleryRoute
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.navigation.navigateSecurely
import org.akrck02.skyleriearts.ui.card.ImageCard
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.akrck02.skyleriearts.ui.theme.MIN_ROUNDED_SHAPE
import org.akrck02.skyleriearts.ui.theme.TOTAL_ROUNDED_SHAPE

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageDetailView(
    navController: NavHostController,
    data: NavigationType,
    gallery: SnapshotStateMap<String, ImageData>
) {

    val imageData = gallery[data.imageData.name] ?: data.imageData

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(end = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {

            val colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = MaterialTheme.colors.primary
            )

            iconButton(
                icon = Icons.Rounded.Delete,
                description = "Remove",
                colors = colors,
                onClick = {
                    navController.navigateSecurely(GalleryRoute)
                }
            )

            iconButton(
                icon = Icons.Rounded.Save,
                description = "Save",
                colors = colors,
                onClick = {
                    gallery.remove(data.imageData.name)
                    imageData.name = imageData.name
                    imageData.description = imageData.description
                    gallery[data.imageData.name] = imageData

                    saveGalleryToFile(gallery)
                    navController.navigateSecurely(GalleryRoute)
                }
            )


            iconButton(
                icon = Icons.Rounded.Close,
                description = "Close",
                colors = colors,
                onClick = { navController.navigateSecurely(GalleryRoute) }
            )
        }

        Row(modifier = Modifier.fillMaxSize()) {
            Form(data, imageData)


            Column(
                modifier = Modifier.padding(top = 20.dp).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {

                val projectList = mutableListOf<String>()
                projectList.addAll(imageData.projects)

                val projects by remember { mutableStateOf(projectList) }

                chipContainer("Projects") {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 100.dp),
                        modifier = Modifier.padding(start = 40.dp, end = 40.dp).fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        items(items = projects, key = { it }) {
                            Chip(
                                onClick = {},
                                colors = ChipDefaults.chipColors(
                                    backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = BackgroundOpacity)
                                ),
                                modifier = Modifier.padding(end = 10.dp)
                            ) {
                                Text(
                                    it,
                                    modifier = Modifier.padding(
                                        start = 10.dp,
                                        end = 10.dp,
                                        top = 5.dp,
                                        bottom = 5.dp
                                    )
                                )
                            }
                        }
                    }

                }

                var tags by remember { mutableStateOf(imageData.tags) }

                chipContainer("tags") {

                }
            }
        }
    }
}

@Composable
private fun iconButton(
    colors: ButtonColors,
    icon: ImageVector,
    description: String = "",
    onClick: () -> Unit,

    ) {
    Button(
        onClick = onClick,
        colors = colors,
        shape = TOTAL_ROUNDED_SHAPE,
        border = null,
        elevation = null,
        modifier = Modifier.width(50.dp),
    ) {
        Icon(icon, description)
    }
}

@Composable
private fun chipContainer(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {
        Text(
            title,
            fontSize = 1.8.em,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Row {
            content()
        }
    }
}

@Composable
private fun Form(
    data: NavigationType,
    imageData: ImageData,
) {
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(.5f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageCard(
            imageData = data.imageData,
            round = true,
            modifier = Modifier.size(200.dp).padding(20.dp),
        )

        var imageName by remember { mutableStateOf(imageData.name) }

        val width = 350.dp
        TextField(
            value = imageName,
            onValueChange = {
                imageName = it
            },
            label = { Text("Name") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = DEFAULT_ROUNDED_SHAPE,
            modifier = Modifier.padding(10.dp).width(width),
            enabled = false
        )

        var imageDescription by remember { mutableStateOf(imageData.description) }
        TextField(
            value = imageDescription,
            onValueChange = {
                imageDescription = it
            },
            label = { Text("Description") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = MIN_ROUNDED_SHAPE,
            modifier = Modifier.padding(10.dp).height(200.dp).width(width)
        )
    }
}