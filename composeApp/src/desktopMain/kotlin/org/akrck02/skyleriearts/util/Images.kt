package org.akrck02.skyleriearts.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image
import java.nio.file.Files
import java.nio.file.Path


fun loadImageFrom(filePath: String): ImageBitmap {
    val bytes = Files.readAllBytes(Path.of(filePath)) // path relative to project root
    return Image.makeFromEncoded(bytes).toComposeImageBitmap()
}

fun resizeImage(image: Bitmap, maxWidth: Int, maxHeight: Int) {


}