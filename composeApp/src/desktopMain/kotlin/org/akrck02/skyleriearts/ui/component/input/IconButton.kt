package org.akrck02.skyleriearts.ui.component.input

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    modifier: Modifier = Modifier.width(50.dp),
    iconModifier: Modifier = Modifier.width(50.dp),
    contentPadding: PaddingValues = PaddingValues(10.dp)
) {
    Button(
        onClick = data.onClick,
        colors = colors,
        shape = TOTAL_ROUNDED_SHAPE,
        border = null,
        elevation = null,
        contentPadding = contentPadding,
        modifier = modifier.size(50.dp).pointerHoverIcon(PointerIcon.Hand),
    ) { Icon(data.icon, data.description, modifier = iconModifier) }
}

@Composable
fun IconButtonLarge(
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    data: IconButtonData,
) {
    Button(
        onClick = data.onClick,
        colors = colors,
        shape = ButtonDefaults.elevatedShape,
        modifier = Modifier.height(60.dp).pointerHoverIcon(PointerIcon.Hand)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = data.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(end = 10.dp)
            )

            Icon(
                imageVector = data.icon,
                contentDescription = data.description,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}