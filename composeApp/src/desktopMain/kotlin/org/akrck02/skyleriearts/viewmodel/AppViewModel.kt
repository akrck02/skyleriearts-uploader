package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import org.akrck02.skyleriearts.core.processor.FileProcessor
import org.akrck02.skyleriearts.core.processor.ImageProcessor
import org.akrck02.skyleriearts.core.service.FileUploader
import org.akrck02.skyleriearts.extension.toggle
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.Route
import org.akrck02.skyleriearts.navigation.UploadRoute
import org.akrck02.skyleriearts.navigation.isCurrentRoute
import org.akrck02.skyleriearts.navigation.navigateSecurely
import java.io.File

class AppViewModel : ViewModel() {

    lateinit var navHostController: NavHostController
    var currentRoute: Route by mutableStateOf(UploadRoute)

    var gallery: SnapshotStateMap<String, ImageData> = FileProcessor.getCurrentGalleryFromFile()
    var categoryMap: SnapshotStateMap<String, MutableSet<String>> = mutableStateMapOf<String, MutableSet<String>>()
    var projectMap: SnapshotStateMap<String, MutableSet<ImageData>> = mutableStateMapOf()

    init {
        loadGallery()
    }

    fun loadGallery() {
        gallery.forEach { k, image ->
            image.categories.forEach { category ->

                if (null == categoryMap[category]) categoryMap[category] = mutableSetOf<String>()

                image.projects.forEach { project ->
                    categoryMap[category]?.add(project)

                    if (null == projectMap[project]) projectMap[project] = mutableSetOf<ImageData>()
                    projectMap[project]?.add(image)
                }
            }
        }
    }

    fun save() {
        FileProcessor.saveGalleryToFile(gallery)
    }

    fun uploadAndSave() {
        FileProcessor.saveGalleryToFile(gallery)
        FileUploader.uploadCurrentFiles()
    }

    fun addImageFileToGallery(file: File) {
        ImageProcessor.addImageFileToGallery(file, gallery)
        save()
    }

    fun addFileToResources(path: String): File {
        return FileProcessor.addFileToResources(path)
    }

    fun toggleSelection(image: ImageData) {
        image.selected = image.selected.toggle()
        gallery[image.name]!!.selected = image.selected
    }

    fun navigate(route: Route) {
        navHostController.navigateSecurely(route, this)
        currentRoute = route
    }

    @Composable
    fun isCurrentRoute(route: Route): Boolean {
        return navHostController.isCurrentRoute(route)
    }

}