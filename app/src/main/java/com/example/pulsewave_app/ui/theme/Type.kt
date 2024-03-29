package com.example.pulsewave_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pulsewave_app.R

// Set of Material typography styles to start with
val Typography = Typography(
        bodyLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
        )
        /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val OpenSans = FontFamily(
        Font(R.font.open_sans, FontWeight(500), FontStyle.Normal),
        Font(R.font.open_sans, FontWeight(500), FontStyle.Italic)
)

val Asap = FontFamily(
        Font(R.font.asap_regular, FontWeight(500), FontStyle.Normal),
        Font(R.font.asap_italic, FontWeight(500), FontStyle.Italic),
        Font(R.font.asap_bold, FontWeight(700), FontStyle.Normal),
        Font(R.font.asap_bolditalic, FontWeight(700), FontStyle.Italic),
)