package org.akrck02.skyleriearts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.akrck02.skyleriearts.ui.drag.DragComposable
import org.akrck02.skyleriearts.ui.theme.getSystemThemeColors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = getSystemThemeColors()
    ) {

        val systemColors = getSystemThemeColors()
        Box(
            modifier = Modifier.background(systemColors.background).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hi Skylerie!",
                    color = systemColors.primary,
                    fontSize = 2.em,
                    modifier = Modifier.padding(20.dp)
                )
                DragComposable("Drag your images here")
                Button(onClick = { }) {
                    Text("Upload")
                }
            }
        }
    }
}