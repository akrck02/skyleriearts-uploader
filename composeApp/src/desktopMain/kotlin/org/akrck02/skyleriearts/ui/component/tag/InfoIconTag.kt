package org.akrck02.skyleriearts.ui.component.tag

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun InfoIconTag(name: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Text(
            text = name,
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.overline,
            modifier = Modifier.padding(end = 10.dp)
        )

        Icon(
            imageVector = icon,
            contentDescription = name,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(28.dp)
        )
    }
}
