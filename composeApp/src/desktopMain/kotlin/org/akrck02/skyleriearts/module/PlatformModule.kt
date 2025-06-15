package org.akrck02.skyleriearts.module

import org.akrck02.skyleriearts.ui.view.image.detail.ImageDetailViewModel
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import org.akrck02.skyleriearts.viewmodel.ProjectsViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule
        )
    }

// Add viewmodel classes here to be loaded inside the database module.
val viewModelModule = module {
    viewModelOf(::AppViewModel)
    viewModelOf(::ProjectsViewModel)
    viewModelOf(::ImageDetailViewModel)
}