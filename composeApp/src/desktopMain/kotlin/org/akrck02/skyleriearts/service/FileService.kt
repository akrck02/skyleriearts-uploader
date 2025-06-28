package org.akrck02.skyleriearts.service

import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.eygraber.uri.UriCodec
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.akrck02.skyleriearts.data.constant.Paths
import org.akrck02.skyleriearts.data.constant.validExtensions
import org.akrck02.skyleriearts.data.model.Image
import org.akrck02.skyleriearts.exception.ErrorCode
import org.akrck02.skyleriearts.exception.SoftwareException
import org.akrck02.skyleriearts.extension.buildMutableStateMap
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

object FileService : IFileService {

    override fun add(path: String): File {

        // If it is directory return
        val file = File(path)
        if (file.isDirectory) throw SoftwareException(code = ErrorCode.FilePathIsDirectory, message = "Cannot delete: File $path is a directory.")

        // If extension is invalid return
        if (validExtensions.contains(file.extension).not()) throw SoftwareException(
            code = ErrorCode.InvalidFileExtension,
            message = "Cannot delete: File $path has an invalid extension (${file.extension})."
        )

        // if the file exists return the file
        var newFile = File(Paths.getUploadsAbsolutePath(file.name))
        if (newFile.exists())
            newFile.delete()

        // Copy the file and return
        newFile = file.copyTo(newFile)

        // compress the image
        ImageManipulationService.compress(newFile.path)
        return newFile
    }

    override fun remove(path: String) {

        // If it is directory return
        val file = File(path)
        if (file.isDirectory)
            throw SoftwareException(
                code = ErrorCode.FilePathIsDirectory,
                message = "Cannot delete: File $path is a directory."
            )

        // If file cannot be deleted
        if (file.exists().not())
            throw SoftwareException(
                code = ErrorCode.FileDoesNotExist,
                message = "Cannot delete: File $path doesn't exist."
            )

        // If file cannot be deleted
        if (file.delete().not())
            throw SoftwareException(
                code = ErrorCode.CannotDeleteFile,
                message = "Cannot delete: File $path."
            )

    }

    override fun getGallery(): SnapshotStateMap<String, Image> {
        val currentFile = File(Paths.galleryFilePath)
        if (currentFile.exists().not()) {
            Files.createDirectories(java.nio.file.Paths.get(currentFile.parent))
            currentFile.writeText(
                Json.encodeToString<Map<String, Image>>(mapOf()),
                Charsets.UTF_8
            )
        }

        val jsonData = currentFile.readText(Charsets.UTF_8)
        val map = Json.decodeFromString<Map<String, Image>>(UriCodec.decode(jsonData))
        return buildMutableStateMap {
            map.entries.forEach { (k, v) -> put(k, v) }
        }
    }

    override fun saveGallery(gallery: Map<String, Image>) {
        val currentFile = File(Paths.galleryFilePath)
        currentFile.writeText(
            Json.encodeToString<Map<String, Image>>(gallery),
            Charsets.UTF_8
        )
    }

    override fun loadImage(path: String): ImageBitmap {
        val bytes = Files.readAllBytes(Path.of(path)) // path relative to project root
        return org.jetbrains.skia.Image.makeFromEncoded(bytes).toComposeImageBitmap()
    }
}