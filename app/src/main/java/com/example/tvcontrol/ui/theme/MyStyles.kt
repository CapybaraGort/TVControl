package com.example.tvcontrol.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object MyStyle {
    val text_H1 = TextStyle(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    )

    val text_H2 = TextStyle(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
    )

    val text_H3 = TextStyle(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    )

    val text_P = TextStyle(
        fontFamily = displayFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    )
}