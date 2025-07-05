package org.akrck02.skyleriearts.ui.component.input

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.akrck02.skyleriearts.ui.theme.getTextFieldThemeColors


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DropdownMenu(
    options: List<String>,
    defaultValue: String,
    onChange: (String) -> Unit
) {

    var value by remember { mutableStateOf(defaultValue) }
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = Modifier.padding(top = 0.dp).pointerHoverIcon(PointerIcon.Hand),
    ) {
        TextField(
            value = value,
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .width(250.dp)
                .pointerHoverIcon(PointerIcon.Hand),
            readOnly = true,
            shape = DEFAULT_ROUNDED_SHAPE,
            onValueChange = { },
            colors = getTextFieldThemeColors(),
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "expand more"
                )
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
                        )
                    },
                    onClick = {
                        value = option
                        expanded = false
                        onChange(value)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
                )
            }
        }
    }
}