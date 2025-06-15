package org.akrck02.skyleriearts.ui.component.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE

@Composable
fun ListHeader(title: String) {
    Row(modifier = Modifier.padding(start = 40.dp, end = 40.dp, bottom = 20.dp, top = 40.dp).fillMaxWidth()) {
        Surface(
            shape = DEFAULT_ROUNDED_SHAPE,
            color = Color(0xFFE9E5DD),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                    Modifier.padding(start = 30.dp, end = 30.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.overline
                )
            }
        }
    }
}
