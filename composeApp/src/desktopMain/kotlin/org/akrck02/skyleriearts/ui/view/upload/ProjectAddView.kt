package org.akrck02.skyleriearts.ui.view.upload

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.akrck02.skyleriearts.extension.removeIfPresent
import org.akrck02.skyleriearts.ui.component.input.DropdownMenu
import org.akrck02.skyleriearts.ui.component.input.IconButton
import org.akrck02.skyleriearts.ui.component.input.IconButtonBasicData
import org.akrck02.skyleriearts.ui.component.input.MaterialTextField
import org.akrck02.skyleriearts.ui.component.tag.TagContainer
import org.akrck02.skyleriearts.viewmodel.ProjectAddViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import skylerieartsuploader.composeapp.generated.resources.Res
import skylerieartsuploader.composeapp.generated.resources.name

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

        Text(
            text = "Add a category.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            lineHeight = 50.sp,
            modifier = Modifier.padding(top = 40.dp, bottom = 15.dp).width(450.dp)
        )

        Row {

            if (viewModel.globalCategories.isEmpty().not()) {
                CategorySelector(viewModel)
                IconButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    data = IconButtonBasicData(
                        icon = Icons.Outlined.Add,
                        description = "Add",
                        onClick = {

                            println("Adding ${viewModel.selectedCategory}")
                            viewModel.suputamadre(viewModel.selectedCategory)
                            // viewModel.projectCategories.add(viewModel.selectedCategory)
                            viewModel.globalCategories.removeIfPresent(viewModel.selectedCategory)
                            viewModel.selectedCategory = viewModel.globalCategories.firstOrNull() ?: ""

                            println(viewModel.projectCategories)
                        }
                    ),
                    modifier = Modifier.padding(start = 10.dp),
                    rounded = false
                )
            }

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
                rounded = false
            )
        }

        CategoryCards(viewModel)
    }
}

@Composable
private fun CategorySelector(viewModel: ProjectAddViewModel) {
    var value = remember { viewModel.selectedCategory }
    DropdownMenu(
        options = viewModel.globalCategories,
        defaultValue = value,
        onChange = {
            println("$it selected")
            viewModel.selectedCategory = it
        }
    )
}

@Composable
private fun CategoryCards(viewModel: ProjectAddViewModel) {
    var categories = remember { viewModel.projectCategories }
    println("Printing cards")

    Row(
        horizontalArrangement = Arrangement.Center
    ) {

        TagContainer(
            tags = categories,
            emptyText = "",
            interactable = true,
            onAdd = {},
            onRemove = {}
        )
    }
}