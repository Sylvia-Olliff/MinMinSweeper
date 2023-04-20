package com.minminsweeper.ui.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.rememberDialogState
import com.minminsweeper.ui.style.*

@Composable
fun TimeLimitDialog(
    onCloseRequest: () -> Unit
) {
    Dialog(
        onCloseRequest = { onCloseRequest() },
        state = rememberDialogState(size = DpSize(dialogSize / 2, dialogSize / 2)),
        title = "",
        resizable = false
    ) {
        OutOfTimeError(onCloseRequest)
    }
}

@Composable
private fun OutOfTimeError(
    onCloseRequest: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = TIME_OUT,
            modifier = Modifier.padding(smallPad),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall
        )
        OutlinedButton(
            onClick = { onCloseRequest() },
            modifier = Modifier.padding(smallPad),
            border = BorderStroke(tinyPad, NumberColors.colorMap[COLORS.BLUE]!!)
        ) {
            Text(text = START_GAME, style = MaterialTheme.typography.labelSmall)
        }
    }
}
