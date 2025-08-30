package org.akrck02.skyleriearts.ui.view.upload

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.akrck02.skyleriearts.extension.getLastUrlSection
import org.akrck02.skyleriearts.ui.component.input.DropdownMenu
import org.akrck02.skyleriearts.ui.component.input.IconButton
import org.akrck02.skyleriearts.ui.component.input.IconButtonBasicData
import org.akrck02.skyleriearts.ui.component.input.IconButtonLarge
import org.akrck02.skyleriearts.viewmodel.ImageAddViewModel
import org.koin.compose.viewmodel.koinViewModel
import java.io.File
import kotlin.math.max
import kotlin.math.min

@Composable
fun ImageAddView(addedImageList: MutableList<File>, viewModel: ImageAddViewModel = koinViewModel()) {

    var openNewProjectView by remember { mutableStateOf(false) }
    if (openNewProjectView) {
        ProjectAddView(onClose = {
            openNewProjectView = false
            viewModel.loadProjects()
        })
        return
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "The following pictures will be added.",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            lineHeight = 50.sp,
            modifier = Modifier.padding(bottom = 50.dp).width(450.dp)
        )

        AddedGalleryPreview(addedImageList)

        Text(
            text = "Select a project.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            lineHeight = 50.sp,
            modifier = Modifier.padding(top = 50.dp, bottom = 15.dp).width(450.dp)
        )

        ProjectSelector(viewModel)

        Text(
            text = "or",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            lineHeight = 50.sp,
            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp).width(450.dp)
        )

        IconButtonLarge(
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            data = IconButtonBasicData(
                icon = Icons.Rounded.Add,
                description = "Create a new one",
                onClick = { openNewProjectView = true }
            )
        )

        IconButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            data = IconButtonBasicData(
                icon = Icons.Rounded.Save,
                description = "Save",
                onClick = {}
            ),
            modifier = Modifier.padding(top = 50.dp)
        )
    }
}

@Composable
private fun ProjectSelector(viewModel: ImageAddViewModel) {
    var value = remember { viewModel.selectedProject }
    DropdownMenu(
        options = viewModel.projects,
        defaultValue = value,
        onChange = { value = it }
    )
}

@Composable
private fun AddedGalleryPreview(addedImageList: MutableList<File>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(top = 0.dp)
    ) {
        var size = min(3, addedImageList.size - 1)
        for (i in 0..size) {
            Text(
                text = addedImageList[i].absolutePath.getLastUrlSection().substring(1),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp,
            )
        }

        val extra = max(addedImageList.size - 4, 0)
        if (0 != extra) {
            Text(
                text = "+$extra",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 36.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}