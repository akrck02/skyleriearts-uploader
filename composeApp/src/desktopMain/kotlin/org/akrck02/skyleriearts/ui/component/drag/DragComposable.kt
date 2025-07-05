package org.akrck02.skyleriearts.ui.component.drag

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.TextFieldDefaults.BackgroundOpacity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragData
import androidx.compose.ui.draganddrop.dragData
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import io.github.vinceglb.filekit.core.FileKit
import io.github.vinceglb.filekit.core.PickerMode
import io.github.vinceglb.filekit.core.PickerType
import kotlinx.coroutines.launch
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE
import java.io.File
import java.net.URLDecoder

/***
 * Composable for File Drag and Drop
 * @param text The text to show
 * @param onDrop Function to execute on drag
 * @param onFileAdded Function to execute on file added
 */
@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun DragComposable(
    text: String,
    onStarted: () -> Unit = {},
    onDrop: (String) -> File?,
    onFinish: () -> Unit = {}
) {

    var corroutineScope = rememberCoroutineScope()
    var showTargetBorder by remember { mutableStateOf(false) }
    val dragAndDropTarget = remember {
        object : DragAndDropTarget {

            // Highlights the border of a potential drop target
            override fun onStarted(event: DragAndDropEvent) {
                showTargetBorder = true
            }

            override fun onEnded(event: DragAndDropEvent) {
                showTargetBorder = false
                onStarted()
            }

            override fun onDrop(event: DragAndDropEvent): Boolean {


                // Get transferable data and handle it
                val fileList = event.dragData() as DragData.FilesList
                fileList.readFiles().forEach { url ->

                    if (url.startsWith("file:").not()) {
                        return@forEach
                    }

                    val newUrl = decode(url.removePrefix("file:"))
                    onDrop(newUrl)
                }

                onFinish()
                return true
            }

        }
    }

    Surface(
        shape = DEFAULT_ROUNDED_SHAPE,
        modifier = Modifier.width(500.dp)
            .height(300.dp)
            .padding(30.dp)
            .dragAndDropTarget(
                shouldStartDragAndDrop = { true },
                target = dragAndDropTarget
            )
            .pointerHoverIcon(PointerIcon.Hand),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = BackgroundOpacity),
        onClick = {

            corroutineScope.launch {

                // FileKit Core
                val files = FileKit.pickFile(
                    type = PickerType.Image,
                    mode = PickerMode.Multiple(),
                    title = "Pick an image",
                )

                if (files.isNullOrEmpty().not()) {
                    onStarted()
                    files.forEach { file ->
                        onDrop(file.path ?: "")
                    }
                    onFinish()
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(PaddingValues(80.dp, 20.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextIconPrimary(text, Icons.Outlined.Image)
        }
    }

}

fun decode(url: String): String = URLDecoder.decode(url, "UTF-8")

/**
 * Text icon composable
 * @param text The text to show
 * @param icon The icon to show
 */
@Composable
private fun TextIconPrimary(text: String, icon: ImageVector) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(70.dp).padding(10.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary
        )
    }
}