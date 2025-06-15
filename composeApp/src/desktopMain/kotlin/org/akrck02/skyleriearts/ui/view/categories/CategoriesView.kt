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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.akrck02.skyleriearts.model.ProjectListFilter
import org.akrck02.skyleriearts.navigation.ProjectsRoute
import org.akrck02.skyleriearts.ui.component.header.ListHeader
import org.akrck02.skyleriearts.ui.component.tag.InfoIconTag
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import skylerieartsuploader.composeapp.generated.resources.gallery

/**
 * The category view
 *
 * @param gallery The gallery to show
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesView(appViewModel: AppViewModel) {
    val categories by mutableStateOf(appViewModel.categoryMap.keys.toMutableStateList().sorted())
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            stickyHeader { ListHeader("You have ${appViewModel.categoryMap.size} categories right now.") }

            items(categories) { category ->
                CategoryRow(appViewModel, category) {
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
private fun CategoryRow(appViewModel: AppViewModel, name: String, callback: () -> Unit) {
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
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.overline
                )

                val imageNumber = appViewModel.run { categoryMap[name]?.sumOf { projectMap[it]?.size ?: 0 } ?: 0 }

                Row {
                    InfoIconTag("${appViewModel.categoryMap[name]?.size}", Icons.Outlined.Palette)
                    InfoIconTag("$imageNumber", Icons.Outlined.Collections)
                }
            }
        }
    }
}
