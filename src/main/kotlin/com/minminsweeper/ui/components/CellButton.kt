package com.minminsweeper.ui.components

import androidx.compose.runtime.Composable
import com.minminsweeper.game.board.Cell

@Composable
internal fun CellButton(
    gameState: Any,
    cell: Cell,
    onSelectCell: (Pair<Int, Int>) -> Unit,
    onFlagCell: (Pair<Int, Int>) -> Unit
) {

}