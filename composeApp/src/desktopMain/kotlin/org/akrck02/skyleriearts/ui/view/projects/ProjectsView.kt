package org.akrck02.skyleriearts.ui.view.projects

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.onClick
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.akrck02.skyleriearts.constant.ImagesRoute
import org.akrck02.skyleriearts.constant.ProjectsRoute
import org.akrck02.skyleriearts.ui.component.header.FilterHeader
import org.akrck02.skyleriearts.ui.component.header.ListHeader
import org.akrck02.skyleriearts.ui.component.tag.InfoIconTag
import org.akrck02.skyleriearts.ui.model.filter.GalleryFilter
import org.akrck02.skyleriearts.ui.model.filter.ProjectListFilter
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import org.akrck02.skyleriearts.viewmodel.ProjectsViewModel
import org.koin.compose.viewmodel.koinViewModel
import skylerieartsuploader.composeapp.generated.resources.gallery


/**
 * The projects view
 *
 * @param gallery The gallery to show
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProjectsView(appViewModel: AppViewModel, projectsViewModel: ProjectsViewModel = koinViewModel()) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val projects: List<String> = projectsViewModel.projects
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            stickyHeader {
                ListHeader("You have ${projects.size} projects right now.")
                ProjectsRoute.filter.takeIf { ProjectListFilter.None != it }?.also {
                    ProjectsRoute.filterObjectId?.also { filter ->
                        FilterHeader(listOf(filter to Icons.Rounded.Category)) {
                            ProjectsRoute.filter = ProjectListFilter.None
                            projectsViewModel.reload()
                        }
                    }
                }
            }

            items(projects) { project ->
                ProjectRow(project, projectsViewModel) {
                    ImagesRoute.filter = GalleryFilter.Project
                    ImagesRoute.filterObjectId = project
                    appViewModel.navigate(ImagesRoute)
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProjectRow(name: String, projectsViewModel: ProjectsViewModel, callback: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, bottom = 10.dp, top = 10.dp)
            .fillMaxWidth(0.97f)
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick { callback() },
    ) {
        Surface(
            shape = DEFAULT_ROUNDED_SHAPE,
            color = Color(0xFFFBFAF9),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )

                val imageNumber = projectsViewModel.getImageNumberOfProject(name)
                Row { InfoIconTag("$imageNumber", Icons.Outlined.Collections) }
            }
        }
    }
}
