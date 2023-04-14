package com.minminsweeper.ui.dialogs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.rememberDialogState
import com.minminsweeper.game.GameState
import com.minminsweeper.game.board.Cell
import com.minminsweeper.game.board.CellState
import com.minminsweeper.ui.components.DigitalScreen
import com.minminsweeper.ui.components.MMSCell
import com.minminsweeper.ui.components.ResetButton
import com.minminsweeper.ui.style.*

@Composable
fun RulesDialog(
    onCloseRequest: () -> Unit
) {
    Dialog(
        onCloseRequest = { onCloseRequest() },
        state = rememberDialogState(size = DpSize(dialogSize, dialogSize)),
        title = RULES_MENU,
        resizable = false
    ) {
        MMSRules()
    }
}

@Composable
private fun MMSRules() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DigitalScreen(10)
            ResetButton(GameState.PLAYING) {}
            DigitalScreen(999)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = LEFT_SCREEN,
                modifier = Modifier.width(rulesWidth),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = FACE_CLICK,
                modifier = Modifier.width(rulesWidth),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = RIGHT_SCREEN,
                modifier = Modifier.width(rulesWidth),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Spacer(Modifier.height(windowPad))
        Row(
            modifier = Modifier.padding(vertical = tinyPad).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MMSCell(GameState.WON, Cell(0 to 0, neighbourBombs = 3, state = CellState.SELECTED), {}, {})
        }
        Row(
            modifier = Modifier.padding(vertical = tinyPad).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = NUMBER_CELL,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Spacer(Modifier.height(windowPad))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MouseIcon(isLeftClick = true)
            MouseIcon(isLeftClick = false)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = LEFT_CLICK,
                modifier = Modifier.width(rulesWidth),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = RIGHT_CLICK,
                modifier = Modifier.width(rulesWidth),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MouseIcon(isLeftClick: Boolean) {
    val mouseWidth = 70.dp
    val mouseHeight = 80.dp

    Canvas(
        modifier = Modifier
            .padding(smallPad)
            .requiredSize(mouseWidth, mouseHeight),
        contentDescription = if (isLeftClick) LEFT_CLICK_DESCRIPTION else RIGHT_CLICK_DESCRIPTION
    ) {
        val strokeWidth = 2f

        val xOffsets = if (isLeftClick) listOf(4f, 7f, 2f, 5f, 6f, 10f) else listOf(-4f, -7f, -2f, -5f, -6f, -10f)
        // small strokes depicting movement
        drawLine(
            MinMinSweeperColors.onPrimary,
            Offset((size.width + xOffsets[0]) % size.width, 4f),
            Offset((size.width + xOffsets[1]) % size.width, 6f),
            strokeWidth,
            StrokeCap.Round
        )
        drawLine(
            MinMinSweeperColors.onPrimary,
            Offset((size.width + xOffsets[2]) % size.width, 8f),
            Offset((size.width + xOffsets[3]) % size.width, 9f),
            strokeWidth,
            StrokeCap.Round
        )
        drawLine(
            MinMinSweeperColors.onPrimary,
            Offset((size.width + xOffsets[4]) % size.width, 0f),
            Offset((size.width + xOffsets[5]) % size.width, 4f),
            strokeWidth,
            StrokeCap.Round
        )

        val xOffset = 8f
        val yOffset = 5f

        val bottom = Path().apply {
            moveTo(xOffset, size.height / 2 - 10)
            lineTo(size.width - xOffset, size.height / 2 - 10)
            arcTo(
                Rect(
                    topLeft = Offset(xOffset, size.height / 2 - 10),
                    bottomRight = Offset(size.width - xOffset, size.height - yOffset)
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            close()
        }
        drawPath(bottom, color = MinMinSweeperColors.onPrimary, style = Stroke(width = strokeWidth))

        val buttonXOffset = 3f
        val buttonYOffset = 15f

        val left = Path().apply {
            moveTo(size.width / 2 - buttonXOffset, yOffset)
            lineTo(size.width / 2 - buttonXOffset, size.height / 2 - buttonYOffset)
            lineTo(xOffset, size.height / 2 - buttonYOffset)
            arcTo(
                Rect(
                    topLeft = Offset(xOffset, yOffset),
                    bottomRight = Offset(size.width / 2 - buttonXOffset, size.height / 2 - buttonYOffset)
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            close()
        }
        drawPath(left, MinMinSweeperColors.onPrimary, style = if (isLeftClick) Fill else Stroke(width = strokeWidth))

        val right = Path().apply {
            moveTo(size.width - xOffset, size.height / 2 - buttonYOffset)
            lineTo(size.width / 2 + buttonXOffset, size.height / 2 - buttonYOffset)
            lineTo(size.width / 2 + buttonXOffset, yOffset)
            arcTo(
                Rect(
                    topLeft = Offset(size.width / 2 + buttonXOffset, yOffset),
                    bottomRight = Offset(size.width - xOffset, size.height / 2 - buttonYOffset)
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            close()
        }
        drawPath(right, MinMinSweeperColors.onPrimary, style = if (isLeftClick) Stroke(width = strokeWidth) else Fill)
    }
}
