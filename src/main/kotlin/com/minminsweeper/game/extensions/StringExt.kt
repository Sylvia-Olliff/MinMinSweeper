package com.minminsweeper.game.extensions

fun String.isValid(isHeight: Boolean) = isNotEmpty() && toInt() in 9..(if (isHeight) 30 else 50)

fun String.isValid(row: String, col: String) = isNotEmpty() && toInt() in 5..getMaxMines(row, col)

internal fun getMaxMines(row: String, col: String): Int = row.toInt() * col.toInt() / 5
