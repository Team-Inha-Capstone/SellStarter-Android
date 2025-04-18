package com.inha.sellstarter_android.presentation.mypage.help

data class HelpItem(
    val title : String,
    val url : String
)

val helpItems = listOf(
    HelpItem("- 🆕 신규 스토어 API키 발급 받는 방법", "https://ss99x2002.notion.site/1d85e65acf338016b07fee8cbf3630f5?pvs=73"),
    HelpItem("- 📱 앱 사용 방법", "https://ss99x2002.notion.site/1d85e65acf338016b07fee8cbf3630f5?pvs=73"),
)
