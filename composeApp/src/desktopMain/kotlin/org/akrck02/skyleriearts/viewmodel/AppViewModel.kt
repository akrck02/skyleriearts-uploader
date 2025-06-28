package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import org.akrck02.skyleriearts.constant.Route
import org.akrck02.skyleriearts.constant.UploadRoute
import org.akrck02.skyleriearts.constant.isCurrentRoute
import org.akrck02.skyleriearts.constant.navigateSecurely
import org.akrck02.skyleriearts.data.model.Image
import org.akrck02.skyleriearts.service.FileService
import java.io.File

class AppViewModel : ViewModel() {

    lateinit var navHostController: NavHostController
    var currentRoute: Route by mutableStateOf(UploadRoute)

    fun save() {
        //  FileService.saveGallery(gallery)
    }

    fun uploadAndSave() {
        // FileService.saveGallery(gallery)
        // CloudSyncService.sync()
    }

    fun addImageFileToGallery(file: File) {
        //  ImageProcessor.addImageFileToGallery(file, gallery)
        //  save()
    }

    fun addFileToResources(path: String): File {
        return FileService.add(path)
    }

    fun toggleSelection(image: Image) {
        // image.selected = image.selected.toggle()
        // gallery[image.name]!!.selected = image.selected
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