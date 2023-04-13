package com.minminsweeper.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.PointerMatcher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.onClick
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minminsweeper.game.GameState
import com.minminsweeper.game.board.Cell
import com.minminsweeper.game.board.CellState
import com.minminsweeper.ui.style.*
import com.minminsweeper.ui.utils.drawBevelEdge

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MMSCell(
    gameState: GameState,
    cell: Cell,
    onSelectCell: (Pair<Int, Int>) -> Unit,
    onFlagCell: (Pair<Int, Int>) -> Unit
) {
    Box(
        modifier = Modifier
            .semantics(mergeDescendants = true) {
                testTag = "(${cell.coord.first},${cell.coord.second})"
                role = Role.Button

                if (gameState != GameState.PLAYING || cell.state == CellState.SELECTED) disabled()
            }
            .requiredSize(cellSize)
            .background(color = getBackgroundColor(gameState, cell))
            .drawWithCache {
                onDrawBehind {
                    if (cell.state == CellState.SELECTED || gameState == GameState.LOST && cell.isMine && cell.state == CellState.UNSELECTED) {
                        drawFlatEdge()
                    } else {
                        drawBevelEdge(BEVEL_STROKE_SMALL)
                    }
                }
            }
            .onClick(
                matcher = PointerMatcher.mouse(PointerButton.Primary),
                onClick = { onSelectCell(cell.coord) }
            )
            .onClick(
                matcher = PointerMatcher.mouse(PointerButton.Secondary),
                onClick = { onFlagCell(cell.coord) }
            ),
        contentAlignment = Alignment.Center
    ) {
        when (cell.state) {
            CellState.UNSELECTED -> {
                if (gameState == GameState.LOST && cell.isMine) {
                    Icon(
                        painter = painterResource(MINE_ICON),
                        contentDescription = MINE_DESCRIPTION,
                        modifier = Modifier.padding(cellPadding),
                        tint = Color.Unspecified
                    )
                }
                if (gameState == GameState.WON && cell.isMine) {
                    Icon(
                        painter = painterResource(FLAG_ICON),
                        contentDescription = FLAG_DESCRIPTION,
                        modifier = Modifier.padding(cellPadding),
                        tint = Color.Unspecified
                    )
                }
            }
            CellState.SELECTED -> {
                when (cell.neighbourBombs) {
                    -1 -> Icon(
                        painter = painterResource(MINE_ICON),
                        contentDescription = MINE_DESCRIPTION,
                        modifier = Modifier.padding(cellPadding),
                        tint = Color.Unspecified
                    )
                    0 -> {}
                    else -> Text(
                        text = cell.neighbourBombs.toString(),
                        color = NumberColors.colors[cell.neighbourBombs % 6],
                        textAlign = TextAlign.Center,
                        lineHeight = 15.sp,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
            CellState.FLAGGED -> {
                if (gameState == GameState.LOST && !cell.isMine) {
                    Icon(
                        painter = painterResource(MINE_X_ICON),
                        contentDescription = MINE_X_DESCRIPTION,
                        modifier = Modifier.padding(cellPadding),
                        tint = Color.Unspecified
                    )
                } else {
                    Icon(
                        painter = painterResource(FLAG_ICON),
                        contentDescription = FLAG_DESCRIPTION,
                        modifier = Modifier.padding(cellPadding),
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}

private fun getBackgroundColor(gameState: GameState, cell: Cell): Color {
    return when (cell.state) {
        CellState.UNSELECTED -> if (gameState == GameState.LOST && cell.isMine) {
            MinMinSweeperColors.primary
        } else {
            MinMinSweeperColors.secondary
        }
        CellState.SELECTED -> {
            if (gameState == GameState.LOST && cell.isMine) {
                MinMinSweeperColors.onError
            } else {
                MinMinSweeperColors.primary
            }
        }
        CellState.FLAGGED -> MinMinSweeperColors.secondary
    }
}

private fun DrawScope.drawFlatEdge() {
    drawLine(MinMinSweeperColors.onPrimary, Offset.Zero, Offset(size.width, 0f))
    drawLine(MinMinSweeperColors.onPrimary, Offset.Zero, Offset(0f, size.height))
    drawLine(MinMinSweeperColors.onPrimary, Offset(size.width, 0f), Offset(size.width, size.height))
    drawLine(MinMinSweeperColors.onPrimary, Offset(0f, size.height), Offset(size.width, size.height))
}
