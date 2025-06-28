package org.akrck02.skyleriearts.data

import com.eygraber.uri.UriCodec
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.akrck02.skyleriearts.data.constant.Paths
import org.akrck02.skyleriearts.data.model.Image
import org.akrck02.skyleriearts.data.repository.GalleryRepository
import java.io.File
import java.nio.file.Files

class GalleryDataAccess : GalleryRepository {

    private val gallery = getGallery()

    private fun getGallery(): MutableMap<String, Image> {
        val currentFile = File(Paths.galleryFilePath)
        if (currentFile.exists().not()) {
            Files.createDirectories(java.nio.file.Paths.get(currentFile.parent))
            currentFile.writeText(
                Json.encodeToString<Map<String, Image>>(mapOf()),
                Charsets.UTF_8
            )
        }

        val jsonData = currentFile.readText(Charsets.UTF_8)
        return Json.decodeFromString<MutableMap<String, Image>>(UriCodec.decode(jsonData))
    }

    override fun getCategories(): MutableSet<String> {
        val categories = mutableSetOf<String>()
        gallery.forEach { k, image -> categories.addAll(image.categories) }
        return categories
    }

    override fun searchCategoriesByName(name: String): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun getProjects(): MutableSet<String> {
        val projects = mutableSetOf<String>()
        gallery.forEach { k, image -> projects.addAll(image.projects) }
        return projects
    }

    override fun getProjectsByCategory(category: String): MutableSet<String> {
        val projects = mutableSetOf<String>()
        gallery.filter { it.value.categories.contains(category) }.forEach { projects.addAll(it.value.projects) }
        return projects
    }

    override fun searchProjectsByName(name: String): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun getImage(name: String): Image? = gallery.map { it.value }
        .firstOrNull { it.name == name }

    override fun getImages(): MutableSet<Image> = gallery.map { it.value }
        .toMutableSet()

    override fun getImagesByCategory(category: String): MutableSet<Image> = gallery.map { it.value }
        .filter { it.categories.contains(category) }
        .toMutableSet()

    override fun getImagesByProject(category: String): MutableSet<Image> = gallery.map { it.value }
        .filter { it.projects.contains(category) }
        .toMutableSet()

    override fun searchImagesByName(name: String): MutableSet<Image> {
        TODO("Not yet implemented")
    }
}