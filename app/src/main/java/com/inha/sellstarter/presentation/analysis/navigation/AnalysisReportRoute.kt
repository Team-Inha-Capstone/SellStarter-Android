package com.inha.sellstarter.presentation.analysis.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.inha.sellstarter.presentation.analysis.AnalysisReportScreen
import com.inha.sellstarter.presentation.analysis.AnalysisReportViewModel

@Composable
fun AnalysisReportRoute(
    modifier: Modifier,
    viewModel: AnalysisReportViewModel = hiltViewModel(),
) {
    val graphUrl by viewModel.reportState.collectAsState()

    AnalysisReportScreen(
        reportUrl = graphUrl,
        onDownloadClicked = { },
        modifier = modifier,
    )
}
