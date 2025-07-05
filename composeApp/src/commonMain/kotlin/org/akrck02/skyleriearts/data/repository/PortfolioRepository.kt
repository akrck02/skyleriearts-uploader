package org.akrck02.skyleriearts.data.repository

import org.akrck02.skyleriearts.data.model.Image

interface PortfolioRepository {

    // region categories
    fun getCategories(): Set<String>
    fun searchCategoriesByName(name: String): Set<String>
    fun getCategoriesOfProject(name: String): Set<String>
    fun getCategoriesOfImage(name: String): Set<String>
    // endregion

    // region projects
    fun getProjects(): Set<String>
    fun getProjectsByCategory(category: String): Set<String>
    fun searchProjectsByName(name: String): Set<String>
    // endregion

    // region images
    fun getImage(name: String): Image?
    fun getImages(): Set<Image>
    fun getImagesByCategory(category: String): Set<Image>
    fun getImagesByProject(category: String): Set<Image>
    fun searchImagesByName(name: String): Set<Image>
    // endregion

}