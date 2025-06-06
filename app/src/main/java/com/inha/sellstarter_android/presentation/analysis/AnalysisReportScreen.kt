package com.inha.sellstarter_android.presentation.analysis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.inha.sellstarter_android.presentation.common.component.OneButton
import com.inha.sellstarter_android.presentation.common.screen.TitleScreen
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Purple200

@Composable
fun AnalysisReportScreen(
    modifier: Modifier = Modifier,
    viewModel: AnalysisReportViewModel = hiltViewModel(),
    onDownloadClicked: () -> Unit
) {
    val graphUrl by viewModel.reportState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Grey0)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {

            TitleScreen("스토어 데이터 분석 리포트")

            Spacer(modifier = Modifier.height(4.dp))

            HtmlReportGraphWebView(
                url = graphUrl,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        OneButton(
            text = "보고서(pdf) 다운로드",
            onClick = onDownloadClicked,
            buttonBackgroundColor = Purple200,
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun AnalysisReportScreenPreview() {
    AnalysisReportScreen(
        onDownloadClicked = {},
        modifier = Modifier.fillMaxSize()
    )
}
