package org.akrck02.skyleriearts.ui.component.tag

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldDefaults.BackgroundOpacity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.akrck02.skyleriearts.ui.component.input.IconButton
import org.akrck02.skyleriearts.ui.component.input.IconButtonBasicData
import org.akrck02.skyleriearts.ui.theme.MIN_ROUNDED_SHAPE

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun TagContainer(
    title: String = "Title",
    tags: MutableList<String>,
    icons: ImageVector = Icons.Rounded.Tag,
    contentDescription: String = "",
    emptyText: String = "No elements found.",
    interactable: Boolean = false,
    onAdd: () -> Unit = {},
    onRemove: (String) -> Unit = {}
) {

    val keys: MutableList<String> = remember { mutableListOf() }
    keys.addAll(tags)

    Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            val colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary
            )

            Text(
                title,
                fontSize = 1.8.em,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 10.dp, end = 10.dp)
            )

            if (interactable) {
                IconButton(
                    colors = colors,
                    data = IconButtonBasicData(
                        icon = Icons.Rounded.Edit,
                        description = "Add",
                        onClick = { onAdd() }
                    )
                )
            }
        }

        Row(modifier = Modifier.padding(start = 10.dp)) {

            if (tags.isEmpty()) {
                Text(
                    text = emptyText,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth()
                )
                return
            }


            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                items(items = keys, key = { it }) {
                    Chip(
                        shape = MIN_ROUNDED_SHAPE,
                        onClick = { onRemove(it) },
                        colors = ChipDefaults.chipColors(
                            backgroundColor = MaterialTheme.colorScheme.onSurface.copy(alpha = BackgroundOpacity),
                            contentColor = MaterialTheme.colorScheme.primary,
                            leadingIconContentColor = MaterialTheme.colorScheme.primary,

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

