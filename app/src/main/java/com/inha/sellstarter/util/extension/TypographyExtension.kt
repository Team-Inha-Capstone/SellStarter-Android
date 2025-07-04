package com.inha.sellstarter.util.extension

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

fun Typography.scaledBy(scale: Float): Typography {
    fun TextStyle.scaleFontSize() = this.copy(fontSize = (this.fontSize.value * scale).sp)

    return Typography(
        // Display 계열
        displayLarge = displayLarge.scaleFontSize(),
        displayMedium = displayMedium.scaleFontSize(),
        displaySmall = displaySmall.scaleFontSize(),
        // Headline 계열
        headlineMedium = headlineMedium.scaleFontSize(),
        headlineSmall = headlineSmall.scaleFontSize(),
        // Title 계열
        titleLarge = titleLarge.scaleFontSize(),
        titleMedium = titleMedium.scaleFontSize(),
        // Body 계열
        bodyLarge = bodyLarge.scaleFontSize(),
        bodyMedium = bodyMedium.scaleFontSize(),
        bodySmall = bodySmall.scaleFontSize(),
        // Label
        labelSmall = labelSmall.scaleFontSize(),
        labelMedium = labelMedium.scaleFontSize(),
        labelLarge = labelLarge.scaleFontSize(),
    )
}
