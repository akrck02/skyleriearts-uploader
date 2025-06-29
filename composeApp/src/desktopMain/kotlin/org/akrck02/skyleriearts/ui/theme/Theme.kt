package org.akrck02.skyleriearts.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun getSystemThemeColors(): ColorScheme {
    return lightColorScheme(
        primary = Color(0xFF9A8E75),
        onPrimary = Color(0xFFFFFFFF),

        secondary = Color(0xFF958DA5),
        onSecondary = Color(0xFF9A8E75),

        surface = Color(0xFFDCD8D0),
        onSurface = Color(0xFF9A8E75),

        surfaceContainer = Color(0xFFFBFAF9),

        background = Color(0xFFEDECEA),
        onBackground = Color(0xFF9A8E75),

        error = Color.Red,
        onError = Color.White,
    )
}

@Composable
fun getTextFieldThemeColors() = TextFieldDefaults.colors(
    focusedTextColor = MaterialTheme.colorScheme.primary,
    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
    disabledTextColor = MaterialTheme.colorScheme.onSurface,
    errorTextColor = MaterialTheme.colorScheme.error,

    focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
    disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
    errorContainerColor = MaterialTheme.colorScheme.errorContainer,

    cursorColor = MaterialTheme.colorScheme.primary,
    errorCursorColor = MaterialTheme.colorScheme.error,

    selectionColors = null,

    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    errorIndicatorColor = Color.Transparent,

    focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
    unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurface,
    errorLeadingIconColor = MaterialTheme.colorScheme.error,

    focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
    unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
    errorTrailingIconColor = MaterialTheme.colorScheme.error,

    focusedLabelColor = MaterialTheme.colorScheme.primary,
    unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
    disabledLabelColor = MaterialTheme.colorScheme.onSurface,
    errorLabelColor = MaterialTheme.colorScheme.error,

    focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurface,
    errorPlaceholderColor = MaterialTheme.colorScheme.error,

    focusedSupportingTextColor = MaterialTheme.colorScheme.onSurface,
    unfocusedSupportingTextColor = MaterialTheme.colorScheme.onSurface,
    disabledSupportingTextColor = MaterialTheme.colorScheme.onSurface,
    errorSupportingTextColor = MaterialTheme.colorScheme.error,

    focusedPrefixColor = MaterialTheme.colorScheme.primary,
    unfocusedPrefixColor = MaterialTheme.colorScheme.onSurface,
    disabledPrefixColor = MaterialTheme.colorScheme.onSurface,
    errorPrefixColor = MaterialTheme.colorScheme.error,

    focusedSuffixColor = MaterialTheme.colorScheme.primary,
    unfocusedSuffixColor = MaterialTheme.colorScheme.onSurface,
    disabledSuffixColor = MaterialTheme.colorScheme.onSurface,
    errorSuffixColor = MaterialTheme.colorScheme.error,
)
