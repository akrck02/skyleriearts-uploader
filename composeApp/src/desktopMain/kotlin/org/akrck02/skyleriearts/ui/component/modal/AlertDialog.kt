package org.akrck02.skyleriearts.ui.component.modal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.ui.component.input.MaterialTextField
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE

@Composable
fun MaterialAlertInputDialog(
    title: String = "Title",
    description: String = "Your description here",
    textFieldLabel: String = "label",
    textFieldValue: String = "",
    acceptText: String = "Ok",
    cancelText: String = "Cancel",
    onTextFieldValueChange: (String) -> Unit = {},
    onClose: () -> Unit = {},
    onClickAccept: () -> Unit = {}
) {
    AlertDialog(
        title = { Text(title) },
        text = {
            Column {
                Text(
                    text = description,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Column(
                    modifier = Modifier.padding(0.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    MaterialTextField(
                        value = textFieldValue,
                        onValueChange = onTextFieldValueChange,
                        label = textFieldLabel,
                        modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onClickAccept()
                    onClose()
                },
                modifier = Modifier.padding(end = 10.dp).pointerHoverIcon(PointerIcon.Hand),
                shape = DEFAULT_ROUNDED_SHAPE
            ) { Text(acceptText) }
        },
        dismissButton = {
            Button(
                onClick = onClose,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp,
                    hoveredElevation = 2.dp
                ),
                shape = DEFAULT_ROUNDED_SHAPE
            ) { Text(cancelText) }
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        onDismissRequest = onClose,
        modifier = Modifier.padding(10.dp)
    )
}



