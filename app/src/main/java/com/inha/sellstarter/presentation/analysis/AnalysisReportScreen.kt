package com.inha.sellstarter.presentation.analysis

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inha.sellstarter.presentation.common.component.OneButton
import com.inha.sellstarter.presentation.common.screen.TitleScreen
import com.inha.sellstarter.ui.theme.Grey0
import com.inha.sellstarter.ui.theme.Purple200
import com.inha.sellstarter.util.extension.createWebViewPdf

@Composable
fun AnalysisReportScreen(
    reportUrl: String,
    onDownloadClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var webView: WebView? by remember { mutableStateOf(null) }

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Grey0),
    ) {
        Column(
            modifier =
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
        ) {
            TitleScreen("스토어 데이터 분석 리포트")

            Spacer(modifier = Modifier.height(4.dp))

            HtmlReportWebView(
                url = reportUrl,
                webViewRef = {
                    webView = it // 외부 remember { mutableStateOf<WebView?>() } 에 저장
                },
                modifier =
                    Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(),
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        OneButton(
            text = "보고서(pdf) 다운로드",
            onClick = {
                webView?.let {
                    createWebViewPdf(context, it, "analysis_report.pdf")
                }
            },
            buttonBackgroundColor = Purple200,
            enabled = true,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 24.dp),
        )

        Spacer(modifier = Modifier.height(18.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun AnalysisReportScreenPreview() {
}
