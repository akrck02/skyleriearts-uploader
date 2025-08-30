package org.akrck02.skyleriearts.data

import com.eygraber.uri.UriCodec
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.akrck02.skyleriearts.data.constant.Paths
import org.akrck02.skyleriearts.data.model.Image
import org.akrck02.skyleriearts.data.model.Portfolio
import org.akrck02.skyleriearts.data.repository.PortfolioRepository
import org.akrck02.skyleriearts.extension.damerauLevenshteinDistance
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.nio.file.Files

class PortfolioDataAccess : PortfolioRepository {

    private val maxNameDistance = 10
    private val portfolio = getPortfolioFromDatabase()

    // region database
    /**
     * Get the portfolio from json file
     */
    private fun getPortfolioFromDatabase(): Portfolio {
        val currentFile = File(Paths.galleryFilePath).also {
            if (it.exists().not()) {
                Files.createDirectories(java.nio.file.Paths.get(it.parent))
                it.writeText(Json.encodeToString<Portfolio>(Portfolio()), Charsets.UTF_8)
            }
        }

        val jsonData = currentFile.readText(Charsets.UTF_8)
        return Json.decodeFromString<Portfolio>(UriCodec.decode(jsonData))
    }

    /**
     * Save the portfolio in the database
     */
    fun savePortfolio() {
        BufferedWriter(FileWriter(Paths.galleryFilePath)).use { writer ->
            writer.write(Json.encodeToString(portfolio))
        }
    }

    // endregion
    // region categories

    /**
     * Get all the current categories
     */
    override fun getCategories(): Set<String> {
        return portfolio.categories.keys
    }

    /**
     * Search categories by name
     */
    override fun searchCategoriesByName(name: String): Set<String> {
        return portfolio.categories.keys
    }

    /**
     * Get categories of a project
     */
    override fun getCategoriesOfProject(projectId: String): Set<String> {
        return portfolio.categories.filter { it.value.contains(projectId).not() }.map { it.key }.toSet()
    }

    /**
     * Get categories of image
     */
    override fun getCategoriesOfImage(imageId: String): Set<String> {
        return getImage(imageId)?.categories ?: setOf()
    }

    /**
     * Delete a category
     */
    override fun deleteCategory(categoryId: String) {
        TODO("Not yet implemented")
    }

    private fun removeUnnecessaryCategoriesFromImage(image: Image) {

        val categoriesToDelete = mutableSetOf<String>()
        image.categories.forEach { categoryId ->
            val categoryMustBeDeleted = image.projects.none { portfolio.categories[categoryId]?.contains(it) == true }
            if (categoryMustBeDeleted) categoriesToDelete.add(categoryId)
        }

        categoriesToDelete.forEach { image.categories.remove(it) }
    }

    // endregion
    // region projects

    /**
     * Get projects by name
     */
    override fun getProjects(): Set<String> {
        return mutableSetOf<String>().also { projects ->
            portfolio.categories.forEach { projects.addAll(it.value) }
        }
    }

    /**
     * Get projects by category
     */
    override fun getProjectsByCategory(categoryId: String): Set<String> {
        return portfolio.categories[categoryId] ?: mutableSetOf()
    }

    /**
     * Search projects by name
     */
    override fun searchProjectsByName(name: String): Set<String> {
        return getProjects().filter { name.damerauLevenshteinDistance(it) < maxNameDistance }.toMutableSet()
    }

    /**
     * Insert a new project
     */
    override fun insertProject(projectId: String, categoryId: String) {
        val projectsOfCategory = portfolio.categories[categoryId]
        if (null == projectsOfCategory) {
            portfolio.categories[categoryId] = mutableSetOf(projectId)
            return
        }

        projectsOfCategory.add(projectId)
    }

    /**
     * Delete a project
     */
    override fun deleteProject(projectId: String) {

        // delete the project from categories it belongs to
        portfolio.categories.forEach { it.value.remove(projectId) }

        // remove the categories of this project if needed
        getImagesByProject(projectId).forEach { removeUnnecessaryCategoriesFromImage(it) }

    }

    // endregion
    // region images

    /**
     * Get image by name
     */
    override fun getImage(imageId: String): Image? {
        return portfolio.images[imageId]
    }

    /**
     * Get all the available images
     */
    override fun getImages(): Set<Image> {
        return portfolio.images.values.toMutableSet()
    }

    /**
     * Get all the images of a category
     */
    override fun getImagesByCategory(categoryId: String): Set<Image> {
        return portfolio.images.map { it.value }
            .filter { it.categories.contains(categoryId) }
            .toMutableSet()
    }

    /**
     * Get all the images by project
     */
    override fun getImagesByProject(projectId: String): Set<Image> = portfolio.images
        .map { it.value }
        .filter { it.projects.contains(projectId) }
        .toMutableSet()

    /**
     * Search images by name
     */
    override fun searchImagesByName(name: String): Set<Image> {
        return portfolio.images
            .filter { name.damerauLevenshteinDistance(it.key) < maxNameDistance }
            .map { it.value }
            .toMutableSet()
    }

    /**
     * Insert a new image
     */
    override fun insertImage(image: Image) {
        portfolio.images.put(image.name, image)
    }

    /**
     * Update an existing image
     */
    override fun updateImage(image: Image) {
        portfolio.images.put(image.name, image)
    }

    /**
     * Delete an image
     */
    override fun deleteImage(imageId: String) {
        portfolio.images.remove(imageId)
    }

    /**
     * move a image to another project
     */
    override fun moveImageToProject(imageId: String, projectId: String) {
        TODO("Not yet implemented")
    }

    /**
     * move image to another category
     */
    override fun moveImageToCategory(imageId: String, categoryId: String) {
        TODO("Not yet implemented")
    }

    // endregion

    fun print() {

        portfolio.categories.forEach { category, projects -> println("$category $projects") }
        println()

        portfolio.images.forEach { _, image -> println("$image") }
        println()

    }
}