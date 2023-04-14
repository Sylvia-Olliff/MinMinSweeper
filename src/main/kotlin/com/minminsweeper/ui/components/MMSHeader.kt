package com.minminsweeper.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import com.minminsweeper.game.GameState
import com.minminsweeper.ui.style.BEVEL_STROKE_LARGE
import com.minminsweeper.ui.style.MinMinSweeperColors
import com.minminsweeper.ui.style.windowPad
import com.minminsweeper.ui.utils.drawBevelEdge

@Composable
fun MMSHeader(
    flagsRemaining: Int,
    seconds: Int,
    gameState: GameState,
    onResetRequest: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(color = MinMinSweeperColors.primary)
            .padding(windowPad)
            .drawWithCache {
                onDrawBehind {
                    drawBevelEdge(BEVEL_STROKE_LARGE, isElevated = false)
                }
            }
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DigitalScreen(flagsRemaining)
        ResetButton(gameState, onResetRequest)
        DigitalScreen(seconds)
    }
}
