package com.minminsweeper.game.board

import androidx.compose.runtime.Immutable

@Immutable
data class Cell(
    val coord: Pair<Int, Int>,
    val state: CellState = CellState.UNSELECTED,
    val neighbourBombs: Int = 0
) {
    val isMine: Boolean
        get() = neighbourBombs == -1


}
