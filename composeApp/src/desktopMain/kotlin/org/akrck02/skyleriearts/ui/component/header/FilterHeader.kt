package org.akrck02.skyleriearts.ui.component.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.FilterAlt
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.akrck02.skyleriearts.ui.theme.TOTAL_ROUNDED_SHAPE


@Composable
fun FilterHeader(filters: List<Pair<String, ImageVector>>, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(0.97f).padding(start = 40.dp, end = 40.dp, bottom = 20.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                imageVector = Icons.Rounded.FilterAlt,
                contentDescription = "filter",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp).padding(end = 10.dp)
            )

            filters.forEach { FilterChip(it.first, it.second) }
        }

        ClearFilterButton(onClick)
    }
}


@Composable
private fun ClearFilterButton(onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
    ) {
        Surface(
            shape = TOTAL_ROUNDED_SHAPE,
            color = Color(0xFFFBFAF9),
            onClick = onClick
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            ) {

                Text(
                    text = "Clean filters",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(end = 10.dp)
                )

                Icon(
                    imageVector = Icons.Rounded.Clear,
                    contentDescription = "clean filters",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun FilterChip(name: String, icon: ImageVector) {
    Surface(
        shape = TOTAL_ROUNDED_SHAPE,
        color = Color(0xFFFBFAF9),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
        ) {

            Icon(
                imageVector = icon,
                contentDescription = name,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp).padding(end = 10.dp)
            )
            Text(
                text = name,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}