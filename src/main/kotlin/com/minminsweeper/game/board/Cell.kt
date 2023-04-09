package com.minminsweeper.game.board

data class Cell(
    private val coord: Pair<Int, Int>,
    private var isBomb: Boolean,
    private var digits: Int?,
    private var isChecked: Boolean
) {
    //TODO: Add helper methods for getting/setting status aspects of a cell
}
