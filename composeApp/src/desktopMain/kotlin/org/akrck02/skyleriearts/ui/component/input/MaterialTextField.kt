package org.akrck02.skyleriearts.ui.component.input

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.ui.theme.MIN_ROUNDED_SHAPE
import org.akrck02.skyleriearts.ui.theme.getTextFieldThemeColors


@Composable
fun MaterialTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    label: String = "Label",
    width: Dp = 300.dp,
    modifier: Modifier = Modifier.padding(10.dp).width(width),
    enabled: Boolean = true
) {


    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        colors = getTextFieldThemeColors(),
        shape = MIN_ROUNDED_SHAPE,
        modifier = modifier,
        enabled = enabled
    )


}