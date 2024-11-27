package org.akrck02.skyleriearts.ui.input

import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.ui.theme.TOTAL_ROUNDED_SHAPE

/**
 * Base data for building an icon button
 */
interface IconButtonData {
    val icon: ImageVector
    val description: String
    val onClick: () -> Unit
}

/**
 * Base data class for building an icon button
 */
class IconButtonBasicData(
    override val icon: ImageVector,
    override val description: String,
    override val onClick: () -> Unit
) : IconButtonData

/**
 * Icon button composable
 */
@Composable
fun IconButton(
    colors: ButtonColors,
    data: IconButtonData,
    modifier: Modifier = Modifier.width(50.dp)
) {
    Button(
        onClick = data.onClick,
        colors = colors,
        shape = TOTAL_ROUNDED_SHAPE,
        border = null,
        elevation = null,
        modifier = modifier,
    ) {
        Icon(data.icon, data.description, modifier = Modifier)
    }
}
