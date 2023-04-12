package com.minminsweeper.game.board

import androidx.compose.runtime.Immutable

@Immutable
data class Cell(
    val coord: Pair<Int, Int>,
    val state: CellState = CellState.UNSELECTED,
    val neighborBombs: Int = 0
) {
    val isMine: Boolean
        get() = neighborBombs == -1


}
