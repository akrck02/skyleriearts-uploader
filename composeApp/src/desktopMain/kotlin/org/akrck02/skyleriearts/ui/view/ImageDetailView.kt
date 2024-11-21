package org.akrck02.skyleriearts.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.ui.card.ImageCard
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE

@Composable
fun ImageDetailView(
    navController: NavHostController,
    data: NavigationType,
    gallery: SnapshotStateMap<String, ImageData>
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageCard(
            imageData = data.imageData,
            round = true,
            modifier = Modifier.size(200.dp).padding(20.dp),
        )

        var imageName by remember { mutableStateOf(data.imageData.name) }

        TextField(
            value = imageName,
            onValueChange = { imageName = it },
            label = { Text("Name") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = DEFAULT_ROUNDED_SHAPE,
            modifier = Modifier.padding(10.dp)
        )

        var imageDescription by remember { mutableStateOf(data.imageData.description) }
        TextField(
            value = imageDescription,
            onValueChange = { imageDescription = it },
            label = { Text("Description") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = DEFAULT_ROUNDED_SHAPE,
            modifier = Modifier.padding(10.dp)
        )

        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }


}