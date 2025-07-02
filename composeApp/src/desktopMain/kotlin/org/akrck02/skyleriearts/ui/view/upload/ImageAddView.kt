package org.akrck02.skyleriearts.ui.view.upload

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.akrck02.skyleriearts.ui.component.gallery.GalleryImage
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.akrck02.skyleriearts.ui.theme.getTextFieldThemeColors
import org.akrck02.skyleriearts.viewmodel.ImageAddViewModel
import org.koin.compose.viewmodel.koinViewModel
import java.io.File
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageAddView(addedImageList: MutableList<File>, viewModel: ImageAddViewModel = koinViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "The following pictures will be added.",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            lineHeight = 50.sp,
            modifier = Modifier.padding(bottom = 50.dp).width(450.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 0..3) {

                Row {
                    Surface(
                        modifier = Modifier.height(70.dp).width(80.dp).defaultMinSize(60.dp, 60.dp).padding(start = 5.dp, end = 5.dp),
                        shape = DEFAULT_ROUNDED_SHAPE
                    ) {
                        if (addedImageList.size > i) {
                            GalleryImage(
                                data = org.akrck02.skyleriearts.ui.model.GalleryImage(
                                    name = "",
                                    path = addedImageList[i].absolutePath,
                                    minPath = addedImageList[i].absolutePath
                                ),
                                modifier = Modifier.fillMaxSize().padding(0.dp),
                                onClick = { }
                            )
                        }
                    }
                }

            }

            val extra = max(addedImageList.size - 4, 0)
            if (0 != extra) {
                Text(
                    text = "+$extra",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 36.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }

        println("AAAAAAAAAAA")

        var textFieldState by mutableStateOf(viewModel.projects.first())
        var expanded by remember { mutableStateOf(false) }

        println("BBBBBBBBBBBB")

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier.padding(top = 50.dp).pointerHoverIcon(PointerIcon.Hand),
        ) {
            TextField(
                value = textFieldState,
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable).width(200.dp).pointerHoverIcon(PointerIcon.Hand),
                readOnly = true,
                shape = DEFAULT_ROUNDED_SHAPE,
                onValueChange = { textFieldState = it },
                colors = getTextFieldThemeColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {

                viewModel.projects.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                        onClick = {
                            println(option)
                            textFieldState = option
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
                    )
                }
            }

        }

    }
}