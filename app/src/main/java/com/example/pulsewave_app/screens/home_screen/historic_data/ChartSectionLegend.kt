package com.example.pulsewave_app.screens.home_screen.historic_data

import android.graphics.Typeface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.legend.rememberHorizontalLegend
import com.patrykandpatrick.vico.compose.legend.rememberLegendItem
import com.patrykandpatrick.vico.compose.theme.vicoTheme
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.legend.HorizontalLegend

@Composable
fun chartSectionLegend(): HorizontalLegend {
    return rememberHorizontalLegend(
        items = listOf(
            rememberLegendItem(
                icon = rememberShapeComponent(
                    Shapes.roundedCornerShape(allPercent = 50),
                    Color(0xFFFF8888)
                ),
                label =
                rememberTextComponent(
                    color = vicoTheme.textColor,
                    textSize = 12.sp,
                    typeface = Typeface.DEFAULT_BOLD,
                ),
                labelText = "Systolic",
            ),
            rememberLegendItem(
                icon = rememberShapeComponent(
                    Shapes.roundedCornerShape(allPercent = 50),
                    Color(0xFFCC88FF)
                ),
                label =
                rememberTextComponent(
                    color = vicoTheme.textColor,
                    textSize = 12.sp,
                    typeface = Typeface.DEFAULT_BOLD,
                ),
                labelText = "Diastolic",
            ),
        ),
        iconSize = 8.dp,
        iconPadding = 8.dp,
        spacing = 32.dp,
        padding = dimensionsOf(top = 12.dp),
    )
}