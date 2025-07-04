package com.inha.sellstarter.presentation.mypage.component.appfont

enum class FontSizeType(
    val label: String,
    val scale: Float,
) {
    SMALL("작게", 0.8f),
    MEDIUM("보통", 1.0f),
    LARGE("크게", 1.2f),
    EXTRA_LARGE("아주 크게", 1.5f),
    ;

    companion object {
        fun fromScale(scale: Float): FontSizeType {
            return values().minByOrNull { kotlin.math.abs(it.scale - scale) } ?: MEDIUM
        }
    }
}
