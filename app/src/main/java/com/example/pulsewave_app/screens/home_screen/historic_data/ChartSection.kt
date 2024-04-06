package com.example.pulsewave_app.screens.home_screen.historic_data

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulsewave_app.ui.theme.BloodPressureCardRed
import com.example.pulsewave_app.ui.theme.BloodPressureGlowRed
import com.example.pulsewave_app.ui.theme.NoConnectionCardGrey
import com.example.pulsewave_app.ui.theme.OpenSans
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layout.fullWidth
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.compose.chart.zoom.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.theme.ProvideVicoTheme
import com.patrykandpatrick.vico.compose.theme.vicoTheme
import com.patrykandpatrick.vico.core.chart.layout.HorizontalLayout
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.lineSeries
import com.patrykandpatrick.vico.core.zoom.Zoom


@Composable
fun ChartSection() {
    Column(
        modifier = Modifier
            .padding(24.dp, 0.dp)
    ) {
        var currentHistoryRange by remember { mutableStateOf(historyRanges[0]) }
        var bottomAxisValueFormatter by remember { mutableStateOf(formatters[0]) }
        var positionList by remember { mutableStateOf(positionLists[0]) }
        var systolicList by remember { mutableStateOf(systolicLists[0]) }
        var diastolicList by remember { mutableStateOf(diastolicLists[0]) }
        var initialWindow by remember { mutableFloatStateOf(initialZooms[0]) }
        var xStep by remember { mutableFloatStateOf(xSteps[0]) }
        val zoomState = rememberVicoZoomState(
            zoomEnabled = false,
            initialZoom = Zoom.x(initialWindow)
        )
        val yAxisMinimum = ((((diastolicList.minOrNull()?.minus(10)
            ?: 60) / 10) * 10)).toFloat()
        val yAxisMaximum = ((((systolicList.maxOrNull()?.plus(20)
            ?: 180) / 10) * 10)).toFloat()

        var expanded by remember { mutableStateOf(false) }
        var shouldChartUpdate by remember { mutableStateOf(true) }
        Box {
            Box(
                modifier = Modifier
                    .clickable(onClick = {
                        expanded = !expanded
                    })
            ) {
                Text(
                    text = "$currentHistoryRange  ▼",
                    style = TextStyle(
                        fontFamily = OpenSans,
                        fontWeight = FontWeight(700),
                        fontSize = 18.sp,
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            MaterialTheme(colorScheme = MaterialTheme.colorScheme.copy(surface = Color(0xFF0A0A0A))) {
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    historyRanges.forEach { range ->
                        DropdownMenuItem(
                            onClick = {
                                currentHistoryRange = range
                                bottomAxisValueFormatter = formatters[historyRanges.indexOf(range)]
                                positionList = positionLists[historyRanges.indexOf(range)]
                                systolicList = systolicLists[historyRanges.indexOf(range)]
                                diastolicList = diastolicLists[historyRanges.indexOf(range)]
                                initialWindow = initialZooms[historyRanges.indexOf(range)]
                                xStep = xSteps[historyRanges.indexOf(range)]
                                expanded = false
                                shouldChartUpdate = true
                            },
                            text = {
                                Text(
                                    text = range,
                                    style = TextStyle(
                                        fontFamily = OpenSans,
                                        fontWeight = FontWeight(700),
                                        fontSize = 15.sp,
                                    ),
                                )
                            },
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(20.dp),
                    spotColor = BloodPressureGlowRed,
                )
                .padding(0.dp, 8.dp)
                .background(color = BloodPressureCardRed, shape = (RoundedCornerShape(16.dp)))
                .height(IntrinsicSize.Max)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            val modelProducer = remember { CartesianChartModelProducer.build() }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp, 16.dp, 0.dp, 16.dp)
            ) {
                if (shouldChartUpdate) {
                    LaunchedEffect(Unit) {
                        modelProducer.tryRunTransaction {
                            lineSeries {
                                series(
                                    positionList,
                                    systolicList,
                                )
                                series(
                                    positionList,
                                    diastolicList,
                                )
                            }
                        }
                        shouldChartUpdate = false
                    }
                }

                ProvideVicoTheme(
                    vicoTheme.copy(
                        lineColor = Color(0xAAFFDDDD),
                        textColor = Color(0xAAFFDDDD)
                    )
                ) {
                    CartesianChartHost(
                        chart = rememberCartesianChart(
                            chartSectionLineLayer(max = yAxisMaximum, min = yAxisMinimum),
                            startAxis = rememberStartAxis(),
                            bottomAxis = rememberBottomAxis(
                                valueFormatter = bottomAxisValueFormatter,
                            ),
                            legend = chartSectionLegend()
                        ),
                        modelProducer = modelProducer,
                        zoomState = zoomState,
                        horizontalLayout = HorizontalLayout.fullWidth(),
                        getXStep = { xStep },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

