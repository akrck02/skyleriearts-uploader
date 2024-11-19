package org.akrck02.skyleriearts.ui.drag

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
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
import androidx.compose.ui.draganddrop.awtTransferable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.akrck02.skyleriearts.ui.theme.getSystemThemeColors
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.io.File


@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun DragComposable(text: String) {

    var showTargetBorder by remember { mutableStateOf(false) }
    var targetText by remember { mutableStateOf(text) }
    val coroutineScope = rememberCoroutineScope()
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

                // Get transferable data
                val resText = event.awtTransferable.let(getResourceAsText())

                // If it is directory
                val file = File(resText)
                if (file.isDirectory) {
                    targetText =
                        StringBuilder(file.name)
                            .append(" is a directory").toString()

                    coroutineScope.launch {
                        delay(2000)
                        targetText = text
                    }
                    return false
                }

                // If extension is invalid return
                val validExtensions: Set<String> = setOf("png", "jpg", "gif")
                if (validExtensions.contains(file.extension).not()) {
                    targetText =
                        StringBuilder(".").append(file.extension)
                            .append(" is not a valid extension").toString()

                    coroutineScope.launch {
                        delay(2000)
                        targetText = text
                    }
                    return false
                }


                file.copyTo(File(file.name))

                println(resText)

                // Reverts the text of the drop target to the initial
                // value after 2 seconds.
                coroutineScope.launch {
                    delay(2000)
                    targetText = text
                }
                return false
            }

            private fun getResourceAsText(): (Transferable) -> String = {
                if (it.isDataFlavorSupported(DataFlavor.stringFlavor))
                    it.getTransferData(DataFlavor.stringFlavor) as String
                else
                    it.transferDataFlavors.first().humanPresentableName

            }
        }
    }

    val colors = getSystemThemeColors()
    Column(
        modifier = Modifier.padding(20.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.height(300.dp)
                .width(600.dp)
                .clip(RoundedCornerShape(20))
                .background(colors.surface)
                .padding(20.dp)
                .then(
                    if (showTargetBorder)
                        Modifier.background(colors.primary)
                    else
                        Modifier
                )
                .dragAndDropTarget(
                    // With "true" as the value of shouldStartDragAndDrop,
                    // drag-and-drop operations are enabled unconditionally.
                    shouldStartDragAndDrop = { true },
                    target = dragAndDropTarget
                ),
        ) {
            Text(
                color = colors.primary,
                text = targetText,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}