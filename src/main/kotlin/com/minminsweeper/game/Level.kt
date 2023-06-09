package com.minminsweeper.game

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.minminsweeper.ui.style.cellSize

enum class Level(val values: List<Int>, val timeLimit: Int) {
    BEGINNER(listOf(9, 9, 10), 60 * 20),
    INTERMEDIATE(listOf(16, 16, 40), 60 * 20),
    EXPERT(listOf(16, 30, 99), 60 * 20);

    companion object {
        fun calcWindowWidth(columns: Int) = (columns * cellSize.value).dp + 45.dp
        fun calcWindowHeight(rows: Int) = (rows * cellSize.value).dp + 155.dp
    }

    fun windowWidth(): Dp = calcWindowWidth(values[1])
    fun windowHeight(): Dp = calcWindowHeight(values[0])
}
