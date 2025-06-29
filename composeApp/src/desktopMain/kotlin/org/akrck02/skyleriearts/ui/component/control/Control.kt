package org.akrck02.skyleriearts.ui.component.control

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.ui.component.input.IconButton
import org.akrck02.skyleriearts.ui.component.input.IconButtonData

@Composable
fun ControlsBar(
    buttonList: List<IconButtonData>
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(end = 10.dp),
        horizontalArrangement = Arrangement.End
    ) {

        val colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        )

        buttonList.forEach {
            IconButton(
                colors = colors,
                data = it
            )
        }
    }
}