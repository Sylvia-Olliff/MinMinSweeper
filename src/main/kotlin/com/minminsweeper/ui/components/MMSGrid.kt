package com.minminsweeper.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.platform.testTag
import com.minminsweeper.game.GameState
import com.minminsweeper.game.board.Cell
import com.minminsweeper.ui.style.*
import com.minminsweeper.ui.utils.drawBevelEdge

@Composable
fun MMSGrid(
    rows: Int,
    cols: Int,
    board: List<Cell>,
    gameState: GameState,
    onSelectCell: (Pair<Int, Int>) -> Unit,
    onFlagCell: (Pair<Int, Int>) -> Unit
) {
    Column(
        modifier = Modifier
            .testTag(GRID_TEST_TAG)
            .background(color = MinMinSweeperColors.primary)
            .padding(start = windowPad, end = windowPad, bottom = windowPad)
            .drawWithCache {
                onDrawBehind {
                    drawBevelEdge(BEVEL_STROKE_LARGE, isElevated = false)
                }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(rows) { row ->
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(cols) { col ->
                    key("$row,$col") {
                        MMSCell(gameState, board[row * cols + col], onSelectCell, onFlagCell)
                    }
                }
            }
        }
    }
}
