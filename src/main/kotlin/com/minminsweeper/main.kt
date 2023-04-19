
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.minminsweeper.game.Level
import com.minminsweeper.game.Level.Companion.calcWindowHeight
import com.minminsweeper.game.Level.Companion.calcWindowWidth
import com.minminsweeper.game.MinMinSweeperGameState
import com.minminsweeper.game.board.GameGrid
import com.minminsweeper.ui.MinMinSweeperApp
import com.minminsweeper.ui.dialogs.NewGameDialog
import com.minminsweeper.ui.dialogs.RulesDialog
import com.minminsweeper.ui.dialogs.TimeLimitDialog
import com.minminsweeper.ui.style.*

fun main() = application {
    val windowState = rememberWindowState(
        width = Level.INTERMEDIATE.windowWidth(), height = Level.INTERMEDIATE.windowHeight()
    )
    var gameState by remember { mutableStateOf(MinMinSweeperGameState()) }

    var isNewGameDialogOpen by remember { mutableStateOf(false) }
    var isRulesDialogOpen by remember { mutableStateOf(false) }

    var currentLevel: Level? = Level.INTERMEDIATE

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = WINDOW_TITLE,
        icon = painterResource(MINE_ICON),
        resizable = false
    ) {
        MenuBar {
            Menu(text = OPTIONS_MENU) {
                Item(text = GAME_MENU, mnemonic = 'N') {
                    gameState.timer.pause()
                    isNewGameDialogOpen = true
                }
                Item(text = RULES_MENU, mnemonic = 'R') {
                    gameState.timer.pause()
                    isRulesDialogOpen = true
                }
            }
        }

        MinMinSweeperTheme {
            if (isNewGameDialogOpen) {
                NewGameDialog(currentLevel, { isNewGameDialogOpen = false; gameState.timer.restart() }) {
                    currentLevel = it.first
                    gameState.timer.end()
                    gameState = MinMinSweeperGameState(GameGrid(it.second[0], it.second[1], it.second[2]))
                    windowState.size = windowState.size.copy(
                        currentLevel?.windowWidth() ?: calcWindowWidth(it.second[1]),
                        currentLevel?.windowHeight() ?: calcWindowHeight(it.second[0])
                    )
                    isNewGameDialogOpen = false
                }
            }

            if (isRulesDialogOpen) {
                RulesDialog {
                    isRulesDialogOpen = false
                    gameState.timer.restart()
                }
            }

            if (gameState.timer.outOfTime) {
                TimeLimitDialog { gameState.resetBoard() }
            }

            MinMinSweeperApp(gameState)
        }
    }
}
