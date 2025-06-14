package com.inha.sellstarter_android.presentation.home.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.inha.sellstarter_android.R
import com.inha.sellstarter_android.domain.model.WeeklySales
import com.inha.sellstarter_android.domain.model.YearlySales
import com.inha.sellstarter_android.domain.model.toList
import com.inha.sellstarter_android.presentation.common.component.chip.ChipGroup
import com.inha.sellstarter_android.presentation.model.ChipState
import com.inha.sellstarter_android.ui.theme.Grey0
import com.inha.sellstarter_android.ui.theme.Grey900
import com.inha.sellstarter_android.ui.theme.Purple200
import com.inha.sellstarter_android.ui.theme.Purple50
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.style.ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.DefaultColors
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.column.ColumnChart
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.composed.ComposedChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.composed.plus
import com.patrykandpatrick.vico.core.entry.entryOf
import java.time.LocalDate

@Composable
fun OrderStatisticsContent(
    weeklySales: WeeklySales,
    yearlySales: YearlySales,
    modifier: Modifier = Modifier
) {
    var selectedChipIndex by remember { mutableStateOf(0) }
    val chipLabels = listOf("주별", "월별")

    val chipStates = remember {
        chipLabels.mapIndexed { index, label ->
            ChipState(label, mutableStateOf(index == selectedChipIndex))
        }
    }

    val bottomAxisLabels = if (selectedChipIndex == 1) {
        listOf("1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월")
    } else {
        listOf("월", "화", "수", "목", "금", "토", "일")
    }

    val chartColors = listOf(Purple200)

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "🛒 스토어 주문 추이",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            ChipGroup(
                elements = chipStates,
                onChipClick = { _, _, chipIndex ->
                    selectedChipIndex = chipIndex
                    chipStates.forEachIndexed { index, state ->
                        state.isSelected.value = (index == chipIndex)
                    }
                },
                selectedColor = Purple50,
                unselectedColor = Grey0,
                chipModifier = Modifier.padding(horizontal = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        val data = if (selectedChipIndex == 1) yearlySales.toList() else weeklySales.toList()

        GraphText(
            isMonthlySelected = selectedChipIndex == 1,
            weeklySales = weeklySales,
            yearlySales = yearlySales
        )

        OrderStaticsGraph(
            colorList = chartColors,
            data = data,
            bottomAxisLabels = bottomAxisLabels,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun OrderStaticsGraph(
    modifier: Modifier = Modifier,
    colorList: List<Color>,
    data: List<Int>,
    bottomAxisLabels: List<String>
) {
    val chartModel = remember(data) {
        ChartEntryModelProducer(
            data.mapIndexed { index, value ->
                entryOf(x = index.toFloat(), y = value.toFloat())
            }
        )
    }

    Column(modifier = modifier.fillMaxWidth()) {
        ProvideChartStyle(rememberChartStyle(colorList)) {
            Chart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                chart = createColumnChart(),
                chartModelProducer = chartModel,
                startAxis = createStartAxis(),
                bottomAxis = createBottomAxis(bottomAxisLabels),
                runInitialAnimation = true,
                chartScrollState = rememberChartScrollState()
            )
        }
    }
}

@Composable
fun rememberChartStyle(columnChartColors: List<Color>): ChartStyle {
    val isDarkTheme = isSystemInDarkTheme()
    val defaultColors = if (isDarkTheme) DefaultColors.Dark else DefaultColors.Light

    return remember(columnChartColors, isDarkTheme) {
        ChartStyle(
            axis =
            ChartStyle.Axis(
                axisLabelColor = Color(defaultColors.axisLabelColor),
                axisGuidelineColor = Color(defaultColors.axisGuidelineColor),
                axisLineColor = Color(defaultColors.axisLineColor),
            ),
            columnChart =
            ChartStyle.ColumnChart(
                columns =
                columnChartColors.map { color ->
                    LineComponent(
                        color = color.toArgb(),
                        thicknessDp = 25f,
                        shape = Shapes.cutCornerShape(topLeftPercent = 20, topRightPercent = 20),
                    )
                },
                dataLabel = TextComponent.Builder().build(),
            ),
            lineChart = ChartStyle.LineChart(lines = emptyList()),
            marker = ChartStyle.Marker(),
            elevationOverlayColor = Color(defaultColors.elevationOverlayColor),
        )
    }
}

@Composable
fun GraphText(
    isMonthlySelected: Boolean,
    weeklySales: WeeklySales,
    yearlySales: YearlySales
) {
    val displayText = if (isMonthlySelected) {
        "이번 달 주문량은 ${yearlySales.currentMonthSum}개입니다."
    } else {
        "이번 주 주문량은 ${weeklySales.weeklySum}개입니다."
    }
    Text(
        text = displayText,
        style = MaterialTheme.typography.titleMedium,
        color = Grey900,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

private fun createChartModel(data: List<Int>): ChartEntryModelProducer {
    return ChartEntryModelProducer(
        data.mapIndexed { index, value ->
            entryOf(x = index.toFloat(), y = value.toFloat())
        },
    )
}

@Composable
private fun createColumnChart() =
    columnChart(
        mergeMode = ColumnChart.MergeMode.Grouped,
        axisValuesOverrider = AxisValuesOverrider.fixed(minY = 0f, maxY = 20f),
        spacing = 10.dp,
    )

@Composable
private fun createStartAxis() =
    rememberStartAxis(
        itemPlacer = AxisItemPlacer.Vertical.default(maxItemCount = 6),
    )

@Composable
private fun createBottomAxis(labels: List<String>) =
    rememberBottomAxis(
        itemPlacer = AxisItemPlacer.Horizontal.default(),
        valueFormatter = { value, _ ->
            labels[value.toInt() % (labels.size)]
        },
    )
