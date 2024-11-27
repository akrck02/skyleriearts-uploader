package org.akrck02.skyleriearts.ui.tag

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults.BackgroundOpacity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.akrck02.skyleriearts.ui.input.IconButton
import org.akrck02.skyleriearts.ui.input.IconButtonBasicData
import org.akrck02.skyleriearts.ui.theme.getSystemThemeColors

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun TagContainer(
    title: String = "Title",
    tags: MutableList<String>,
    icons: ImageVector = Icons.Rounded.Tag,
    contentDescription: String = "",
    emptyText: String = "No elements found.",
    onAdd: () -> Unit = {}
) {
    Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {

        Row {
            Text(
                title,
                fontSize = 1.8.em,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = 10.dp, end = 10.dp)
            )

            val colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = MaterialTheme.colors.primary
            )

            IconButton(
                colors = colors,
                data = IconButtonBasicData(
                    icon = Icons.Rounded.Add,
                    description = "Add",
                    onClick = { onAdd() }
                )
            )
        }

        Row {

            if (tags.isEmpty()) {
                Text(text = emptyText, color = getSystemThemeColors().primary)
                return
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                items(items = tags, key = { it }) {
                    Chip(
                        onClick = {},
                        colors = ChipDefaults.chipColors(
                            backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = BackgroundOpacity)
                        ),
                        modifier = Modifier.padding(end = 10.dp),
                        leadingIcon = {
                            Icon(imageVector = icons, contentDescription = contentDescription)
                        }
                    ) {
                        Text(
                            it, modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }
                }
            }
        }
    }
}

