package com.minminsweeper.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.minminsweeper.game.MinMinSweeperGameState
import com.minminsweeper.ui.components.MMSGrid
import com.minminsweeper.ui.components.MMSHeader

@Composable
fun MinMinSweeperApp(state: MinMinSweeperGameState) {
    Column(
        modifier = Modifier.width(IntrinsicSize.Max),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MMSHeader(
            state.flagsRemaining,
            state.timer.seconds,
            state.gameState,
            state::resetBoard
        )
        MMSGrid(
            state.grid.rows,
            state.grid.cols,
            state.gameCells,
            state.gameState,
            state::onLeftClick
        ) {
            state.onRightClick(it)
        }
    }
}
