package org.akrck02.skyleriearts.ui.view.upload

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TextFieldDefaults.BackgroundOpacity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.akrck02.skyleriearts.extension.removeIfPresent
import org.akrck02.skyleriearts.ui.component.input.IconButton
import org.akrck02.skyleriearts.ui.component.input.IconButtonBasicData
import org.akrck02.skyleriearts.ui.component.input.MaterialTextField
import org.akrck02.skyleriearts.ui.component.modal.CustomMaterialAlertInputDialog
import org.akrck02.skyleriearts.ui.theme.MIN_ROUNDED_SHAPE
import org.akrck02.skyleriearts.viewmodel.ProjectAddViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import skylerieartsuploader.composeapp.generated.resources.Res
import skylerieartsuploader.composeapp.generated.resources.name
import skylerieartsuploader.composeapp.generated.resources.notNow

@Composable
fun ProjectAddView(viewModel: ProjectAddViewModel = koinViewModel(), onClose: () -> Unit = {}) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Adding a new project.",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            lineHeight = 50.sp,
            modifier = Modifier.padding(bottom = 25.dp).width(450.dp)
        )

        MaterialTextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = stringResource(Res.string.name),
            width = 380.dp
        )

        var showSelector by remember { mutableStateOf(false) }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 40.dp, bottom = 15.dp).width(450.dp)
        ) {

            Text(
                text = "Categories.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp,
            )

            IconButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                data = IconButtonBasicData(
                    icon = Icons.Outlined.Add,
                    description = "Add",
                    onClick = { showSelector = true }
                ),
                modifier = Modifier.padding(start = 10.dp)
            )

            IconButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                data = IconButtonBasicData(
                    icon = Icons.Outlined.Edit,
                    description = "New",
                    onClick = {}
                ),
                modifier = Modifier.padding(start = 10.dp),
            )
        }

        if (showSelector) {
            CategorySelectorModal(
                viewModel = viewModel,
                onSelect = { category ->
                    showSelector = false
                    if (null == category) return@CategorySelectorModal
                    viewModel.projectCategories.add(category)
                    viewModel.globalCategories.removeIfPresent(category)
                }
            )
        }

        CategoryCards(viewModel)

        // Error checking
        val error = when {
            viewModel.name.isBlank() -> "Add a project name to continue."
            viewModel.projectCategories.isEmpty() -> "No categories provided, add at least one."
            else -> ""
        }

        if (error.isNotBlank()) {
            Text(
                text = error,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp
            )
            return
        }

        // Action button
        IconButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            data = IconButtonBasicData(
                icon = Icons.Rounded.Check,
                description = "Okay",
                onClick = {
                    viewModel.saveCurrentProject()
                    onClose()
                }
            ),
            modifier = Modifier.padding(start = 10.dp)
        )
    }

}

@Composable
private fun CategorySelectorModal(viewModel: ProjectAddViewModel, onSelect: (String?) -> Unit) {
    CustomMaterialAlertInputDialog(
        title = "Select a category",
        cancelText = stringResource(Res.string.notNow),
        onClose = { onSelect(null) },
    ) {
        LazyColumn(horizontalAlignment = Alignment.Start) {
            items(viewModel.globalCategories) { project ->
                Surface(
                    modifier = Modifier.pointerHoverIcon(PointerIcon.Hand).fillMaxWidth().height(55.dp).padding(bottom = 10.dp),
                    shape = MIN_ROUNDED_SHAPE,
                    onClick = { onSelect(project) }
                ) {
                    Row(Modifier.padding(10.dp)) {
                        Text(
                            text = project,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun CategoryCards(viewModel: ProjectAddViewModel) {
    var categories = remember { viewModel.projectCategories }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 10.dp, bottom = 30.dp)
    ) {

        categories.forEach { category ->
            ElevatedFilterChip(
                selected = false,
                leadingIcon = { Icon(imageVector = Icons.Rounded.Category, contentDescription = "") },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = BackgroundOpacity),
                    iconColor = MaterialTheme.colorScheme.primary,
                    labelColor = MaterialTheme.colorScheme.primary,
                ),
                border = null,
                elevation = null,
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand).padding(start = 5.dp, end = 5.dp),
                onClick = {
                    viewModel.projectCategories.removeIfPresent(category)
                    viewModel.globalCategories.add(category)
                },
                label = { Text(category, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)) }
            )

        }
    }
}