package com.inspiredcoda.moviesapp.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    body2 = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    h1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        fontFamily = PoppinsFontFamily
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 25.sp,
        fontFamily = PoppinsFontFamily
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        fontFamily = PoppinsFontFamily
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        fontFamily = PoppinsFontFamily
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)