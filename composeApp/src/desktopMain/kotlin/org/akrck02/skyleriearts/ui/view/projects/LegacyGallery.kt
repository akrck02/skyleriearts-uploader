package org.akrck02.skyleriearts.ui.view.projects


/**
 * Selection mode
 */
enum class SelectionMode {
    Select,
    SelectAll,
    None
}
//
//@Composable
//fun LegacyGalleryView(appViewModel: AppViewModel) {
//    var selectionMode by remember { mutableStateOf(SelectionMode.None) }
//    Column(modifier = Modifier.fillMaxSize()) {
//        GalleryViewHeader(
//            gallery = appViewModel.gallery,
//            onSelectionModeToggled = {
//                selectionMode = when (selectionMode) {
//                    SelectionMode.None,
//                    SelectionMode.SelectAll -> SelectionMode.Select
//
//                    SelectionMode.Select -> SelectionMode.None
//                }
//            }
//        )
//
//        LazyGallery(
//            gallery = appViewModel.gallery,
//            selectionMode = selectionMode,
//            onImageClick = {
//                appViewModel.navigate(ImageDetailRoute(NavigationType(it)))
//            },
//            onSelectedImageToggle = {
//                appViewModel.toggleSelection(it)
//                selectionMode = SelectionMode.Select
//            }
//        )
//    }
//}
//
///**
// * The header for the gallery view
// *
// * @param gallery The gallery to control
// * @param onSelectionModeToggled Callback for selection mode toggle
// */
//@Composable
//private fun GalleryViewHeader(
//    gallery: SnapshotStateMap<String, Image>,
//    onSelectionModeToggled: () -> Unit
//) {
//    Surface(
//        shape = TOTAL_ROUNDED_SHAPE,
//        modifier = Modifier.fillMaxWidth().height(100.dp)
//            .padding(20.dp),
//        color = Color(0xFFE7E5E1),
//        contentColor = MaterialTheme.colors.primary,
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//        ) {
//            GalleryViewHeaderTitle(gallery)
//            GalleryViewHeaderControls(
//                gallery = gallery,
//                onSelectionModeToggled = onSelectionModeToggled
//            )
//        }
//    }
//}
//
///**
// * The header title
// *
// * @param gallery The current gallery
// */
//@Composable
//private fun GalleryViewHeaderTitle(gallery: SnapshotStateMap<String, Image>) {
//    Row {
//        Icon(
//            imageVector = Icons.Outlined.AutoAwesomeMosaic,
//            contentDescription = stringResource(Res.string.gallery),
//            modifier = Modifier.padding(15.dp).size(35.dp)
//        )
//
//        Text(
//            text = stringResource(Res.string.numberOfImages, gallery.size),
//            modifier = Modifier.padding(top = 12.dp),
//            fontSize = 1.5.em
//        )
//    }
//}
//
///**
// * The controls of the gallery view
// *
// * @param onSelectionModeToggled Callback for selection mode toggle
// */
//@Composable
//private fun GalleryViewHeaderControls(
//    gallery: SnapshotStateMap<String, Image>,
//    onSelectionModeToggled: () -> Unit
//) {
//
//    var selected by remember { mutableStateOf(false) }
//    Row {
//
//        if (selected) {
//            IconButton(
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = Color.Transparent,
//                    contentColor = MaterialTheme.colors.primary
//                ),
//                data = IconButtonBasicData(
//                    icon = Icons.Outlined.DeleteOutline,
//                    description = "Delete",
//                    onClick = {
//                        gallery.forEach { (name, image) ->
//                            if (image.selected) {
//                                ImageProcessor.deleteFromGallery(image, gallery)
//                                gallery.remove(name)
//                            }
//                        }
//                    }
//                ),
//                modifier = Modifier.height(70.dp),
//                iconModifier = Modifier.size(30.dp)
//            )
//        }
//
//        IconButton(
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color.Transparent,
//                contentColor = MaterialTheme.colors.primary
//            ),
//            data = IconButtonBasicData(
//                icon = if (selected) Icons.Outlined.Circle else Icons.Outlined.TaskAlt,
//                description = "Select",
//                onClick = {
//                    selected = !selected
//                    onSelectionModeToggled()
//                }
//            ),
//            modifier = Modifier.height(70.dp),
//            iconModifier = Modifier.size(30.dp)
//        )
//    }
//}
//
///**
// * Lazy image gallery
// *
// * @param gallery The gallery
// * @param selectionMode The selection mode
// * @param onImageClick Callback for regular mode image click
// * @param onSelectedImageToggle Callback for image selection
// */
//@Composable
//private fun LazyGallery(
//    gallery: SnapshotStateMap<String, Image>,
//    selectionMode: SelectionMode,
//    onImageClick: (Image) -> Unit,
//    onSelectedImageToggle: (Image) -> Unit
//) {
//    val minSize = 150.dp
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(minSize),
//        modifier = ProjectViewDefault.lazyGridModifier,
//        verticalArrangement = Arrangement.Top,
//    ) {
//
//        val keys: MutableList<String> = mutableListOf()
//        keys.addAll(gallery.keys)
//
//        items(keys, key = { it }) {
//
//            val image by remember {
//                mutableStateOf(gallery[it]!!.let {
//                    it.copy(path = "${Paths.basePath}/${it.path}", minPath = "${Paths.basePath}/${it.minPath}")
//                })
//            }
//
//            var selected by remember { mutableStateOf(image.selected) }
//
//            GalleryImage(
//                data = image,
//                modifier = ProjectViewDefault.imageModifier(minSize),
//                selected = (selectionMode == SelectionMode.SelectAll || selectionMode == SelectionMode.Select) && selected,
//                grayscale = (selectionMode == SelectionMode.SelectAll || selectionMode == SelectionMode.Select) && selected.not(),
//                onClick = {
//                    when (selectionMode) {
//                        SelectionMode.SelectAll,
//                        SelectionMode.Select -> {
//                            onSelectedImageToggle(image)
//                            selected = image.selected
//                        }
//
//                        SelectionMode.None -> {
//                            onImageClick(image)
//                        }
//                    }
//                }
//            )
//
//        }
//    }
//}
