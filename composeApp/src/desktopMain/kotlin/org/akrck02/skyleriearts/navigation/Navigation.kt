package org.akrck02.skyleriearts.navigation

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import com.eygraber.uri.UriCodec
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.akrck02.skyleriearts.model.ImageData

@Serializable
data class NavigationType(
    val imageData: ImageData
)

val AppNavigationType = object : NavType<NavigationType>(
    isNullableAllowed = false,
) {
    override fun get(bundle: Bundle, key: String): NavigationType? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): NavigationType {
        return Json.decodeFromString(UriCodec.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: NavigationType) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun serializeAsValue(value: NavigationType): String {
        return UriCodec.encode(Json.encodeToString(value))
    }
}