package org.akrck02.skyleriearts.ui.drag

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.awtTransferable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.ui.theme.defaultRoundedShape
import org.akrck02.skyleriearts.ui.theme.getSystemThemeColors
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.io.File

/***
 * Composable for File Drag and Drop
 * @param text The text to show
 * @param onDrag Function to execute on drag
 * @param onFileAdded Function to execute on file added
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DragComposable(text: String, onDrag: (String) -> File?, onFileAdded: (File) -> Unit) {

    var showTargetBorder by remember { mutableStateOf(false) }
    val dragAndDropTarget = remember {
        object : DragAndDropTarget {

            // Highlights the border of a potential drop target
            override fun onStarted(event: DragAndDropEvent) {
                showTargetBorder = true
            }

            override fun onEnded(event: DragAndDropEvent) {
                showTargetBorder = false
            }

            override fun onDrop(event: DragAndDropEvent): Boolean {

                // Get transferable data and handle it
                val result = onDrag(event.awtTransferable.let(getResourceAsText()))
                result?.let {
                    onFileAdded(result)
                    return true
                }

                return false
            }


            /**
             * Get the resource data as text
             */
            private fun getResourceAsText(): (Transferable) -> String = {
                if (it.isDataFlavorSupported(DataFlavor.stringFlavor))
                    it.getTransferData(DataFlavor.stringFlavor) as String
                else
                    it.transferDataFlavors.first().humanPresentableName

            }
        }
    }

    DragAndDrop(showTargetBorder, dragAndDropTarget, text)

}

@Composable
private fun DragAndDrop(
    showTargetBorder: Boolean,
    dragAndDropTarget: DragAndDropTarget,
    targetText: String
) {
    val colors = getSystemThemeColors()
    Column(
        modifier = Modifier.padding(PaddingValues(80.dp, 20.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = dragBoxModifier(colors, showTargetBorder, dragAndDropTarget),
            contentAlignment = Alignment.Center
        ) {
            TextIconPrimary(colors, targetText, Icons.Outlined.Image)
        }
    }
}

/**
 * Drag Box Modifier
 * @param colors The app color theme
 * @param dragging If something is being dragged
 * @param dragAndDropTarget The logical target
 */
@OptIn(ExperimentalFoundationApi::class)
private fun dragBoxModifier(
    colors: Colors,
    dragging: Boolean,
    dragAndDropTarget: DragAndDropTarget
): Modifier {

    return Modifier.height(300.dp)
        .width(600.dp)
        .clip(defaultRoundedShape)
        .background(colors.surface)
        .padding(20.dp)
        .then(
            if (dragging)
                Modifier.clip(defaultRoundedShape)
            else
                Modifier
        )
        .dragAndDropTarget(
            // With "true" as the value of shouldStartDragAndDrop,
            // drag-and-drop operations are enabled unconditionally.
            shouldStartDragAndDrop = { true },
            target = dragAndDropTarget
        )
}

/**
 * Text icon composable
 * @param colors The app color theme
 * @param text The text to show
 * @param icon The icon to show
 */
@Composable
private fun TextIconPrimary(colors: Colors, text: String, icon: ImageVector) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            tint = colors.primary,
            contentDescription = text,
            modifier = Modifier.size(70.dp).padding(10.dp)
        )

        Text(
            color = colors.primary,
            text = text
        )
    }
}