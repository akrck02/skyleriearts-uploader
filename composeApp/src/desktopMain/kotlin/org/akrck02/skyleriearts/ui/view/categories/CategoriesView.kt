package org.akrck02.skyleriearts.ui.view.categories

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
import androidx.compose.material.icons.outlined.Palette
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
import org.akrck02.skyleriearts.constant.ProjectsRoute
import org.akrck02.skyleriearts.ui.component.header.ListHeader
import org.akrck02.skyleriearts.ui.component.tag.InfoIconTag
import org.akrck02.skyleriearts.ui.model.filter.ProjectListFilter
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import org.akrck02.skyleriearts.viewmodel.CategoryViewModel
import org.koin.compose.viewmodel.koinViewModel
import skylerieartsuploader.composeapp.generated.resources.gallery

/**
 * The category view
 *
 * @param gallery The gallery to show
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesView(appViewModel: AppViewModel, categoryViewModel: CategoryViewModel = koinViewModel()) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            stickyHeader { ListHeader("You have ${categoryViewModel.categories.size} categories right now.") }

            items(categoryViewModel.categories) { category ->
                CategoryRow(category, categoryViewModel) {
                    ProjectsRoute.filter = ProjectListFilter.Category
                    ProjectsRoute.filterObjectId = category
                    appViewModel.navigate(ProjectsRoute)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategoryRow(name: String, categoryViewModel: CategoryViewModel, callback: () -> Unit) {
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
                modifier =
                    Modifier.padding(start = 30.dp, end = 30.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )

                val projectNumber = categoryViewModel.getProjectNumberOfCategory(name)
                val imageNumber = categoryViewModel.getImageNumberOfCategory(name)

                Row {
                    InfoIconTag("$projectNumber", Icons.Outlined.Palette)
                    InfoIconTag("$imageNumber", Icons.Outlined.Collections)
                }
            }
        }
    }
}
