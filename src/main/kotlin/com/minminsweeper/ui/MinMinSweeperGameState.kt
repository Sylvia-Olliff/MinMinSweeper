package com.minminsweeper.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.minminsweeper.game.GameState
import com.minminsweeper.game.Level
import com.minminsweeper.game.board.GameGrid
import com.minminsweeper.game.board.Grid
import com.minminsweeper.ui.utils.Timer

class MinMinSweeperGameState(
    val grid: Grid = GameGrid(Level.INTERMEDIATE.values[0], Level.INTERMEDIATE.values[1], Level.INTERMEDIATE.values[2]),
    val timer: Timer = Timer(Level.INTERMEDIATE.timeLimit)
) {
    val flagsRemaining: Int
        get() = grid.flagsRemaining

    private val isGameWon: Boolean
        get() = grid.allBombsFound

    private var gamePaused = false

    var gameState by mutableStateOf(GameState.PLAYING)
    var gameCells by mutableStateOf(grid.cells)

    fun onLeftClick(coords: Pair<Int, Int>) {
        if (gamePaused) return
        if (timer.seconds == 0) timer.start()
        if (grid.selectCell(coords)) {
            if (isGameWon) {
                timer.end()
                gameState = GameState.WON
            }
        } else {
            timer.end()
            gameState = GameState.LOST
        }
        gameCells = grid.cells
    }

    fun onRightClick(coords: Pair<Int, Int>) {
        if (gamePaused) return
        if (timer.seconds == 0) timer.start()
        grid.flagCell(coords)
        gameCells = grid.cells
    }

    fun pauseGame() {
        timer.pause()
    }

    fun unPauseGame() {
        timer.restart()
    }

    fun resetBoard() {
        timer.reset()
        grid.reset()
        gameState = GameState.PLAYING
        gameCells = grid.cells
    }
}
