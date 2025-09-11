package com.insantech.personalitytest.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.insantech.personalitytest.R

private val PlusJakarta = FontFamily(
    Font(R.font.plusjakarta_sans_regular, FontWeight.Normal),
    Font(R.font.plusjakarta_sans_bold, FontWeight.Bold)
)

val Typography = Typography(
    titleLarge = TextStyle(fontFamily = PlusJakarta, fontSize = 26.sp, fontWeight = FontWeight.Bold),
    bodyLarge = TextStyle(fontFamily = PlusJakarta, fontSize = 16.sp),
)