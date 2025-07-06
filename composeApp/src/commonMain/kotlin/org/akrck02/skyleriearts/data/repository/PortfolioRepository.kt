package org.akrck02.skyleriearts.data.repository

import org.akrck02.skyleriearts.data.model.Image

interface PortfolioRepository {

    // region categories
    fun getCategories(): Set<String>
    fun searchCategoriesByName(name: String): Set<String>
    fun getCategoriesOfProject(projectId: String): Set<String>
    fun getCategoriesOfImage(imageId: String): Set<String>

    fun deleteCategory(categoryId: String)
    // endregion

    // region projects
    fun getProjects(): Set<String>
    fun getProjectsByCategory(id: String): Set<String>
    fun searchProjectsByName(name: String): Set<String>

    fun insertProject(projectId: String, categoryId: String)
    fun deleteProject(projectId: String)
    // endregion

    // region images
    fun getImage(imageId: String): Image?
    fun getImages(): Set<Image>
    fun getImagesByCategory(categoryId: String): Set<Image>
    fun getImagesByProject(categoryId: String): Set<Image>
    fun searchImagesByName(name: String): Set<Image>

    fun insertImage(image: Image)
    fun updateImage(image: Image)
    fun deleteImage(imageId: String)

    fun moveImageToProject(imageId: String, projectId: String)
    fun moveImageToCategory(imageId: String, categoryId: String)
    // endregion

}