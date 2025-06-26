package com.inha.sellstarter_android.presentation.analysis

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inha.sellstarter_android.domain.usecase.dataanalysis.LoadDataReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisReportViewModel @Inject constructor(
    private val dataReportUseCase: LoadDataReportUseCase
) : ViewModel() {

    private val _reportState = MutableStateFlow<String>("")
    val reportState: StateFlow<String> = _reportState

    init {
        fetchReport()
    }

    private fun fetchReport() {
        viewModelScope.launch {
           dataReportUseCase().onSuccess { result ->
               Log.e("hyeon", result)
               _reportState.value = result
           }
        }
    }
}
