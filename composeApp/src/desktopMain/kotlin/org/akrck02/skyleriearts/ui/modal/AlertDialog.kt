package org.akrck02.skyleriearts.ui.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.ui.input.MaterialTextField
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
        text = { Text(description) },
        buttons = {

            Column(
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MaterialTextField(
                    value = textFieldValue,
                    onValueChange = onTextFieldValueChange,
                    label = textFieldLabel,
                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
                )
            }

            Row(
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        onClickAccept()
                        onClose()
                    },
                    modifier = Modifier.padding(end = 10.dp),
                    shape = DEFAULT_ROUNDED_SHAPE
                ) {
                    Text(acceptText)
                }

                Button(
                    onClick = onClose,
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        hoveredElevation = 2.dp
                    ),
                    shape = DEFAULT_ROUNDED_SHAPE
                ) {
                    Text(cancelText)
                }
            }


        },
        onDismissRequest = onClose,
        modifier = Modifier.padding(10.dp),
        backgroundColor = MaterialTheme.colors.background
    )
}