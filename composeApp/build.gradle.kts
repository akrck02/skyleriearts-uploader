import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization") version "2.0.20"
}

repositories {
    google()
    mavenCentral()
    maven {
        setUrl("https://jitpack.io")
    }
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.materialIconsExtended)
            implementation(compose.material3)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            // navigation compose
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.8.0-alpha10")

            // serialization
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

            // uri
            implementation("com.eygraber:uri-kmp:0.0.18")

            //shapes
            implementation("androidx.compose:compose-bom:2024.10.01")
            implementation("androidx.graphics:graphics-shapes:1.0.0-alpha05")

            implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")

            // env
            implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")

            // koin
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.composeVM)

            // ViewModel support in common code
            implementation(libs.androidx.lifecycle.viewmodel)

            // File picker
            implementation("io.github.vinceglb:filekit-core:0.8.7")
            implementation("io.github.vinceglb:filekit-compose:0.8.7")


        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}



compose.desktop {
    application {
        mainClass = "org.akrck02.skyleriearts.MainKt"

        nativeDistributions {
            linux {
                modules("jdk.security.auth")
            }
            targetFormats(TargetFormat.Rpm)
            packageName = "org.akrck02.skyleriearts"
            packageVersion = "1.1.0"
        }
    }
}

