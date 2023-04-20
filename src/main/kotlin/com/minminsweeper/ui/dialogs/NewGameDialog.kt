package com.minminsweeper.ui.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.rememberDialogState
import com.minminsweeper.game.Level
import com.minminsweeper.game.extensions.getMaxMines
import com.minminsweeper.game.extensions.isValid
import com.minminsweeper.ui.style.*

@Composable
fun NewGameDialog(
    selectedLevel: Level?,
    onCloseRequest: () -> Unit,
    onNewGame: (Pair<Level?, List<Int>>) -> Unit
) {
    Dialog(
        onCloseRequest = { onCloseRequest() },
        state = rememberDialogState(size = DpSize(dialogSize, dialogSize)),
        title = GAME_MENU,
        resizable = false
    ) {
        GameOptions(selectedLevel, onNewGame)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GameOptions(
    selectedLevel: Level?,
    onNewGame: (Pair<Level?, List<Int>>) -> Unit
) {
    var selected by remember { mutableStateOf(selectedLevel) }
    var rows by remember { mutableStateOf("") }
    var columns by remember { mutableStateOf("") }
    var mines by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .padding(vertical = smallPad, horizontal = cellSize),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (header in listOf(NG_HEADER_HEIGHT, NG_HEADER_WIDTH, NG_HEADER_MINES)) {
                Text(
                    text = header.padEnd(header.length + 1),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }

        for (level in Level.values()) {
            OptionsRow(level, selected == level) { selected = it }
        }

        Row(
            modifier = Modifier
                .testTag(LEVEL_CUSTOM)
                .selectable(
                    selected = selected == null,
                    role = Role.RadioButton,
                    onClick = { selected = null }
                )
                .padding(smallPad)
        ) {
            RadioButton(
                selected = selected == null,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = NumberColors.colorMap[COLORS.BLUE]!!,
                    unselectedColor = MinMinSweeperColors.onPrimary
                )
            )

            Spacer(Modifier.width(tinyPad * 2))

            Text(
                text = LEVEL_CUSTOM.padEnd(13),
                style = MaterialTheme.typography.titleSmall
            )

            OutlinedTextField(
                value = rows,
                onValueChange = { rows = it },
                modifier = Modifier.testTag("$LEVEL_CUSTOM Rows").size(inputSize),
                enabled = selected == null,
                textStyle = MaterialTheme.typography.titleSmall,
                isError = !rows.isValid(isHeight = true),
                singleLine = true
            )

            Spacer(Modifier.width(smallPad))

            OutlinedTextField(
                value = columns,
                onValueChange = { columns = it },
                modifier = Modifier.testTag("$LEVEL_CUSTOM Columns").size(inputSize),
                enabled = selected == null,
                textStyle = MaterialTheme.typography.titleSmall,
                isError = !columns.isValid(isHeight = false),
                singleLine = true
            )

            Spacer(Modifier.width(smallPad))

            OutlinedTextField(
                value = mines,
                onValueChange = { mines = it },
                modifier = Modifier.testTag("$LEVEL_CUSTOM Mines").size(inputSize + smallPad, inputSize),
                enabled = selected == null,
                textStyle = MaterialTheme.typography.titleSmall,
                isError = rows.isEmpty() || columns.isEmpty() || !mines.isValid(rows, columns),
                singleLine = true
            )
        }

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = {
                    onNewGame(
                        selected to (selected?.values ?: listOf(rows.toInt(), columns.toInt(), mines.toInt()))
                    )
                },
                modifier = Modifier.padding(smallPad),
                enabled = selected != null || (rows.isValid(isHeight = true) && columns.isValid(isHeight = false) && mines.isValid(rows, columns)),
                border = BorderStroke(tinyPad, NumberColors.colorMap[COLORS.BLUE]!!)
            ) {
                Text(text = START_GAME, style = MaterialTheme.typography.labelSmall)
            }

            if (selected == null) {
                if (!rows.isValid(isHeight = true)) {
                    Text(
                        text = HEIGHT_ERROR_TEXT,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.titleSmall
                    )
                } else if (!columns.isValid(isHeight = false)) {
                    Text(
                        text = WIDTH_ERROR_TEXT,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.titleSmall
                    )
                } else if (!mines.isValid(rows, columns)) {
                    Text(
                        text = "$MINES_ERROR_TEXT ${getMaxMines(rows, columns)}",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}

@Composable
private fun OptionsRow(
    level: Level,
    isSelected: Boolean,
    onLevelSelect: (Level) -> Unit
) {
    Row(
        modifier = Modifier
            .testTag(level.name)
            .selectable(
                selected = isSelected,
                role = Role.RadioButton,
                onClick = { onLevelSelect(level) }
            )
            .padding(smallPad),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = NumberColors.colorMap[COLORS.BLUE]!!,
                unselectedColor = MinMinSweeperColors.onPrimary
            )
        )

        Spacer(Modifier.width(tinyPad * 2))

        Text(
            text = level.name.padEnd(15),
            style = MaterialTheme.typography.titleSmall
        )

        for ((i, number) in level.values.withIndex()) {
            Text(
                text = number.toString().padEnd(if (i == 0) 7 else 6),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}
