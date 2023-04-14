package com.minminsweeper.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import com.minminsweeper.game.GameState
import com.minminsweeper.ui.style.*
import com.minminsweeper.ui.utils.drawBevelEdge

@Composable
internal fun ResetButton(
    gameState: GameState,
    onResetRequest: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(smallPad)
            .requiredSize(headerHeight)
            .clickable(
                onClickLabel = FACE_CLICK,
                role = Role.Button,
                onClick = { onResetRequest() }
            )
            .background(color = MinMinSweeperColors.primary)
            .drawWithCache {
                onDrawBehind {
                    drawBevelEdge(BEVEL_STROKE_SMALL)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(when (gameState) {
                GameState.PLAYING -> RESET_DEFAULT_ICON
                GameState.WON -> RESET_WON_ICON
                GameState.LOST -> RESET_LOST_ICON
            }),
            contentDescription = when (gameState) {
                GameState.PLAYING -> RESET_DEFAULT_DESCRIPTION
                GameState.WON -> RESET_WON_DESCRIPTION
                GameState.LOST -> RESET_LOST_DESCRIPTION
            },
            modifier = Modifier.padding(tinyPad),
            tint = Color.Unspecified
        )
    }
}
