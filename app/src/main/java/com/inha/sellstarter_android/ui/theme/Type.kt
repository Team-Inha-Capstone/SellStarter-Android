package com.inha.sellstarter_android.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.inha.sellstarter_android.R

// Set of Material typography styles to start with
val pretendard = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.pretendard_light, FontWeight.Light, FontStyle.Normal)
)

val gmarketSans = FontFamily(
    Font(R.font.gmarketsans_ttf_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.gmargetsans_ttf_medium,FontWeight.Normal, FontStyle.Normal)
)

val appTitle = TextStyle(
    fontSize = 42.sp,
    fontFamily = gmarketSans,
    fontWeight = FontWeight.Bold
)

val Typography = Typography(
    displayLarge = TextStyle( // h1
        fontSize = 30.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold
    ),
    displayMedium = TextStyle( // h2
        fontSize = 26.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold
    ),
    displaySmall = TextStyle( // h3
        fontSize = 24.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold
    ),
    headlineMedium = TextStyle( // h4
        fontSize = 20.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold
    ),
    headlineSmall = TextStyle( // h5
        fontSize = 16.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold
    ),
    titleLarge = TextStyle( // 본문1
        fontSize = 24.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold
    ),
    titleMedium = TextStyle( // 본문2
        fontSize = 16.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium
    ),
    bodyLarge = TextStyle( // 설명1
        fontSize = 16.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium
    ),
    bodyMedium = TextStyle( // 설명2
        fontSize = 14.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium
    ),
    bodySmall = TextStyle( // 설명3
        fontSize = 12.sp,
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium
    )
)