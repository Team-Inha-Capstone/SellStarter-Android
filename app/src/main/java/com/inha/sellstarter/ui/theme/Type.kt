package com.inha.sellstarter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.inha.sellstarter.R

// Pretendard 폰트 패밀리 정의
val Pretendard =
    FontFamily(
        Font(R.font.pretendard_bold, FontWeight.Bold, FontStyle.Normal),
        Font(R.font.pretendard_semibold, FontWeight.SemiBold, FontStyle.Normal),
        Font(R.font.pretendard_medium, FontWeight.Medium, FontStyle.Normal),
        Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal),
        Font(R.font.pretendard_light, FontWeight.Light, FontStyle.Normal),
    )

// GmarketSans 폰트 패밀리 정의
val GmarketSans =
    FontFamily(
        Font(R.font.gmarketsans_ttf_bold, FontWeight.Bold, FontStyle.Normal),
        Font(R.font.gmargetsans_ttf_medium, FontWeight.Normal, FontStyle.Normal),
    )

// 앱 제목 스타일
val appTitle =
    TextStyle(
        fontSize = 42.sp,
        fontFamily = GmarketSans,
        fontWeight = FontWeight.Bold,
    )

// 앱 전반의 Typography 설정
val AppTypography =
    Typography(
        // Display 계열: 가장 큰 제목 영역 (H1)
        displayLarge =
            TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = GmarketSans,
            ),
        // Display 계열: 주요 섹션 타이틀 (H2)
        displayMedium =
            TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Pretendard,
            ),
        // Display 계열: 페이지 내 중간 타이틀 (H3)
        displaySmall =
            TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Pretendard,
            ),
        // Headline 계열: 카드/모듈 제목
        headlineMedium =
            TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Pretendard,
            ),
        // Headline 계열: 하위 타이틀/보조 제목
        headlineSmall =
            TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Pretendard,
            ),
        // Title 계열: 본문 강조 스타일
        titleLarge =
            TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Pretendard,
            ),
        titleMedium =
            TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Pretendard,
            ),
        titleSmall =
            TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Pretendard,
            ),
        // Body 계열: 일반 본문 텍스트
        bodyLarge =
            TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = Pretendard,
            ),
        // Body 계열: 설명 텍스트 / 서브 본문
        bodyMedium =
            TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = Pretendard,
            ),
        // Body 계열: 툴팁/주석/보조 설명
        bodySmall =
            TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = Pretendard,
            ),
        // Label/버튼 계열: 버튼/태그
        labelMedium =
            TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Pretendard,
            ),
    )
