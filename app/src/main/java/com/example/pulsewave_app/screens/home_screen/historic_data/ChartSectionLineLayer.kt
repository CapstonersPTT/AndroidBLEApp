package com.example.pulsewave_app.screens.home_screen.historic_data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineSpec
import com.patrykandpatrick.vico.compose.component.shape.shader.color
import com.patrykandpatrick.vico.compose.component.shape.shader.verticalGradient
import com.patrykandpatrick.vico.core.chart.layer.LineCartesianLayer
import com.patrykandpatrick.vico.core.chart.values.AxisValueOverrider
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders

@Composable
fun chartSectionLineLayer (max: Float, min: Float): LineCartesianLayer {
    return rememberLineCartesianLayer(
        axisValueOverrider = AxisValueOverrider.fixed(
            minY = min,
            maxY = max
        ),
        lines = listOf(
            rememberLineSpec(
                shader = DynamicShaders.color(
                    Color(0xFFFF8888)
                ),
                backgroundShader = DynamicShaders.verticalGradient(
                    arrayOf(
                        Color(0xFFFF0000).copy(alpha = 0.25f),
                        Color(0xFFFF8888).copy(alpha = 0f)
                    )
                )
            ),
            rememberLineSpec(
                shader = DynamicShaders.color(
                    Color(0xFFCC88FF)
                ),
                backgroundShader = DynamicShaders.verticalGradient(
                    arrayOf(
                        Color(0xFF8800FF).copy(alpha = 0.25f),
                        Color(0xFFCC88FF).copy(alpha = 0.05f)
                    )
                )
            )
        )
    )
}