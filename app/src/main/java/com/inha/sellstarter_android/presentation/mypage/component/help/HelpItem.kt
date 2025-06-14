package com.inha.sellstarter_android.presentation.mypage.component.help

data class HelpItem(
    val title: String,
    val url: String
)

val helpItems = listOf(
    HelpItem(
        "- 신규 스토어 API키 발급 받는 방법",
        "https://ss99x2002.notion.site/API-Key-2125e65acf3380f58e83cf21279bea2f?source=copy_link"
    ),
    HelpItem(
        "- 앱 사용 방법",
        "https://ss99x2002.notion.site/2125e65acf338061a040da25a302e60a?source=copy_link"
    ),
)
