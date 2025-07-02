package org.akrck02.skyleriearts.module

import org.akrck02.skyleriearts.data.GalleryDataAccess
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import org.akrck02.skyleriearts.viewmodel.CategoryViewModel
import org.akrck02.skyleriearts.viewmodel.GalleryViewModel
import org.akrck02.skyleriearts.viewmodel.ImageAddViewModel
import org.akrck02.skyleriearts.viewmodel.ImageDetailViewModel
import org.akrck02.skyleriearts.viewmodel.ProjectsViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            dataAccessModule,
            viewModelModule
        )
    }

// Add viewmodel classes here to be loaded inside the database module.
val viewModelModule = module {
    viewModelOf(::AppViewModel)
    viewModelOf(::ProjectsViewModel)
    viewModelOf(::CategoryViewModel)
    viewModelOf(::GalleryViewModel)
    viewModelOf(::ImageDetailViewModel)
    viewModelOf(::ImageAddViewModel)
}

// add data access here
val dataAccessModule = module {
    singleOf(::GalleryDataAccess)
}