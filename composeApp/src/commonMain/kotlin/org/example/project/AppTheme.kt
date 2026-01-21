package org.example.project

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.Font
import quizappkmp.composeapp.generated.resources.Res
import quizappkmp.composeapp.generated.resources.notosansjp_regular

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

/**
 * アプリで利用するThemeを定義する
 * fun名は https://material-foundation.github.io/material-theme-builder/ で生成される名前に合わせた.
 */
@Composable
fun AppTheme(content: @Composable () -> Unit) {
    // Webアプリの日本語文字化け問題の対応 - START
    val appFont = FontFamily(Font(Res.font.notosansjp_regular))
    val appTypography = androidx.compose.material3.Typography(
        displayLarge = TextStyle(fontFamily = appFont),
        displayMedium = TextStyle(fontFamily = appFont),
        displaySmall = TextStyle(fontFamily = appFont),
        headlineLarge = TextStyle(fontFamily = appFont),
        headlineMedium = TextStyle(fontFamily = appFont),
        headlineSmall = TextStyle(fontFamily = appFont),
        titleLarge = TextStyle(fontFamily = appFont),
        titleMedium = TextStyle(fontFamily = appFont),
        titleSmall = TextStyle(fontFamily = appFont),
        bodyLarge = TextStyle(fontFamily = appFont),
        bodyMedium = TextStyle(fontFamily = appFont),
        bodySmall = TextStyle(fontFamily = appFont),
        labelLarge = TextStyle(fontFamily = appFont),
        labelMedium = TextStyle(fontFamily = appFont),
        labelSmall = TextStyle(fontFamily = appFont)
    )
    // END

    // ダークテーマ対応 - START
    val darkTheme = isSystemInDarkTheme()
    val colorScheme = when {
        darkTheme -> darkScheme
        else -> lightScheme
    }
    // END

    MaterialTheme(
        colorScheme = colorScheme,
        typography = appTypography,
        content = content
    )
}