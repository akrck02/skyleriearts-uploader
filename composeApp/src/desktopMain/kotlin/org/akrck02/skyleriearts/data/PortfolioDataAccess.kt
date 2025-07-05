package org.akrck02.skyleriearts.data

import com.eygraber.uri.UriCodec
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.akrck02.skyleriearts.data.constant.Paths
import org.akrck02.skyleriearts.data.model.Image
import org.akrck02.skyleriearts.data.model.Portfolio
import org.akrck02.skyleriearts.data.repository.PortfolioRepository
import org.akrck02.skyleriearts.extension.damerauLevenshteinDistance
import java.io.File
import java.nio.file.Files

class PortfolioDataAccess : PortfolioRepository {

    private val maxNameDistance = 10
    private val portfolio = getPortfolioFromDatabase()

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
     * Get all the current categories
     */
    override fun getCategories(): MutableSet<String> {
        return portfolio.categories.keys
    }

    /**
     * Search categories by name
     */
    override fun searchCategoriesByName(name: String): MutableSet<String> {
        return portfolio.categories.keys
    }

    /**
     * Get projects by name
     */
    override fun getProjects(): MutableSet<String> {
        return mutableSetOf<String>().also { projects ->
            portfolio.categories.forEach { projects.addAll(it.value) }
        }
    }

    /**
     * Get projects by category
     */
    override fun getProjectsByCategory(category: String): MutableSet<String> {
        return portfolio.categories[category] ?: mutableSetOf()
    }

    /**
     * Search projects by name
     */
    override fun searchProjectsByName(name: String): MutableSet<String> {
        return getProjects().filter { name.damerauLevenshteinDistance(it) < maxNameDistance }.toMutableSet()
    }

    /**
     * Get image by name
     */
    override fun getImage(name: String): Image? = portfolio.images[name]

    /**
     * Get all the available images
     */
    override fun getImages(): MutableSet<Image> = portfolio.images.values.toMutableSet()

    /**
     * Get all the images of a category
     */
    override fun getImagesByCategory(category: String): MutableSet<Image> {
        val projectsOfCategory = portfolio.categories[category] ?: setOf()
        return portfolio.images.map { it.value }
            .filter { it.projects.any { project -> projectsOfCategory.contains(project) } }
            .toMutableSet()
    }

    /**
     * Get all the images by project
     */
    override fun getImagesByProject(project: String): MutableSet<Image> = portfolio.images
        .map { it.value }
        .filter { it.projects.contains(project) }
        .toMutableSet()

    /**
     * Search images by name
     */
    override fun searchImagesByName(name: String): MutableSet<Image> {
        return portfolio.images
            .filter { name.damerauLevenshteinDistance(it.key) < maxNameDistance }
            .map { it.value }
            .toMutableSet()
    }
}