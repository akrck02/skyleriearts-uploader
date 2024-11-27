package org.akrck02.skyleriearts.ui.control

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.ui.input.IconButton
import org.akrck02.skyleriearts.ui.input.IconButtonData

@Composable
fun ControlsBar(
    buttonList: List<IconButtonData>
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(end = 10.dp),
        horizontalArrangement = Arrangement.End
    ) {

        val colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.primary
        )

        buttonList.forEach {
            IconButton(
                colors = colors,
                data = it
            )
        }
    }
}