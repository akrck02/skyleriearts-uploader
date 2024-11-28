package org.akrck02.skyleriearts.ui.input

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE


@Composable
fun MaterialTextField (
    value: String,
    onValueChange: (String) -> Unit = {},
    label: String = "Label",
    width: Dp = 300.dp,
    modifier: Modifier = Modifier.padding(10.dp).width(width),
    enabled: Boolean = true
) {
    val colors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    );

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        colors = colors,
        shape = DEFAULT_ROUNDED_SHAPE,
        modifier = modifier,
        enabled = enabled
    )


}