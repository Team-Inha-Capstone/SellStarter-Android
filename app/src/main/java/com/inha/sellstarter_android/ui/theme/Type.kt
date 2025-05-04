package com.inha.sellstarter_android.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.inha.sellstarter_android.R

val Pretendard = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.pretendard_light, FontWeight.Light, FontStyle.Normal)
)

val GmarketSans = FontFamily(
    Font(R.font.gmarketsans_ttf_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.gmargetsans_ttf_medium,FontWeight.Normal, FontStyle.Normal)
)

val appTitle = TextStyle(
    fontSize = 42.sp,
    fontFamily = GmarketSans,
    fontWeight = FontWeight.Bold
)

val Typography = Typography(

    // Display 계열 (가장 큰 제목 영역)
    displayLarge = TextStyle( // 30sp → H1 / 앱 홈 타이틀 등
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = GmarketSans
    ),

    displayMedium = TextStyle( // 24sp → H2 / 주요 섹션 타이틀
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Pretendard
    ),

    displaySmall = TextStyle( // 20sp → H3 / 페이지 내 중간 타이틀
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = Pretendard
    ),

    // Headline 계열 (카드, 리스트 제목 등)
    headlineMedium = TextStyle( // 18sp → 카드/모듈 제목
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = Pretendard
    ),

    headlineSmall = TextStyle( // 16sp → 하위 타이틀 / 보조 제목
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = Pretendard
    ),

    // Title 계열 (본문 강조 스타일)
    titleLarge = TextStyle( // 16sp → 본문 강조 / 키워드 강조
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Pretendard
    ),

    titleMedium = TextStyle( // 16sp → 본문 세미볼드 / 키워드 등
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = Pretendard
    ),

    // 본문
    bodyLarge = TextStyle( // 16sp → 일반 본문 텍스트
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = Pretendard
    ),

    bodyMedium = TextStyle( // 14sp → 설명 텍스트 / 서브 본문
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = Pretendard
    ),

    bodySmall = TextStyle( // 12sp → 툴팁 / 주석 / 보조 설명
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = Pretendard
    ),

    // 라벨, 버튼 등
    labelMedium = TextStyle( //  14sp → 버튼 / 태그
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = Pretendard
    )
)
