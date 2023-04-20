package com.minminsweeper.ui.style

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Number Colors
private val MinMinSweeperBlue = Color(0xff80b5ff)
private val MinMinSweeperGreen = Color(0xff80ff80)
private val MinMinSweeperRed = Color(0xffff9580)
private val MinMinSweeperPurple = Color(0xffe699ff)
private val MinMinSweeperOrange = Color(0xffffbf80)
private val MinMinSweeperYellow = Color(0xffffff80)

// Digits
private val DigitRedActive = Color(0xffff4d4d)

// Greyscale
private val VeryLightGray = Color(0xffbdbdbd)
private val LighterGray = Color(0xff757575)
private val Gray = Color(0xff424242)
private val DarkerGray = Color(0xff212121)

enum class COLORS {
    YELLOW, BLUE, GREEN, RED, PURPLE, ORANGE
}

object NumberColors {
    val colorMap = mapOf(
        COLORS.YELLOW to MinMinSweeperYellow,
        COLORS.BLUE to MinMinSweeperBlue,
        COLORS.GREEN to MinMinSweeperGreen,
        COLORS.RED to MinMinSweeperRed,
        COLORS.PURPLE to MinMinSweeperPurple,
        COLORS.ORANGE to MinMinSweeperOrange,
        1 to MinMinSweeperYellow,
        2 to MinMinSweeperBlue,
        3 to MinMinSweeperGreen,
        4 to MinMinSweeperRed,
        5 to MinMinSweeperPurple,
        6 to MinMinSweeperOrange
    )
}

val MinMinSweeperColors = darkColorScheme(
    primary = Gray,
    onPrimary = DarkerGray,
    secondary = LighterGray,
    tertiary = VeryLightGray,
    background = Gray,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xff5d0909),
    onError = DigitRedActive
)

private val MinMinSweeperTypography = Typography(
    titleSmall = TextStyle(
        fontSize = 19.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        letterSpacing = .5.sp,
        textAlign = TextAlign.Left
    ),
    labelSmall = TextStyle(
        color = MinMinSweeperColors.onSurface,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Monospace,
        letterSpacing = .5.sp,
        textAlign = TextAlign.Center
    )
)

@Composable
fun MinMinSweeperTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MinMinSweeperColors,
        typography = MinMinSweeperTypography
    ) {
        Surface(content = content)
    }
}



