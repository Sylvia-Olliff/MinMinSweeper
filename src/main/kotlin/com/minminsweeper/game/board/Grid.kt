package com.minminsweeper.game.board

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.lang.IllegalArgumentException

abstract class Grid(
    val rows: Int,
    val cols: Int,
    protected val mines: Int
) {
    protected val board = List(rows) { row ->
       MutableList(cols) { col ->
            Cell(row to col)
       }
    }

    val cells: List<Cell>
        get() = board.flatten()

    var flagsRemaining by mutableStateOf(mines)
    var allBombsFound by mutableStateOf(false)

    private var unselectedCount = rows * cols

    abstract fun generateMineField()


    protected fun generateNeighbourCount(coords: Pair<Int, Int>) {
        for ((row, col) in getNeighbours(coords)) {
            val neighbor = board[row][col]
            board[row][col] = neighbor.copy(neighbourBombs = neighbor.neighbourBombs + 1)
        }
    }

    private fun getNeighbours(coords: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (row, col) = coords
        val range = listOf(-1, 0, 1)
        val rows = range.map { (it + row).coerceIn(0, this.rows - 1) }.toSet()
        val cols = range.map { (it + col).coerceIn(0, this.cols - 1) }.toSet()
        val neighbours = mutableListOf<Pair<Int, Int>>()
        for (x in rows) {
            for (y in cols) {
                if (x == row && y == col) continue
                val neighbour = board[x][y]
                if (neighbour.state == CellState.UNSELECTED && !neighbour.isMine) {
                    neighbours.add(neighbour.coord)
                }
            }
        }
        return neighbours
    }

    open fun flagCell(coords: Pair<Int, Int>) {
        val cell = board[coords.first][coords.second]
        when (cell.state) {
            CellState.UNSELECTED -> {
                if (flagsRemaining == 0) return
                flagsRemaining--
                board[coords.first][coords.second] = cell.copy(state = CellState.FLAGGED)
            }
            CellState.FLAGGED -> {
                flagsRemaining++
                board[coords.first][coords.second] = cell.copy(state = CellState.UNSELECTED)
            }
            CellState.SELECTED -> throw IllegalArgumentException("Cannot flag a previously selected Cell")
        }
    }

    open fun selectCell(coords: Pair<Int, Int>): Boolean {
        val cell = board[coords.first][coords.second]
        return when (cell.state) {
            CellState.UNSELECTED -> {
                board[coords.first][coords.second] = cell.copy(state = CellState.SELECTED)
                unselectedCount--
                if (cell.isMine) {
                    false
                } else {
                    if (cell.neighbourBombs == 0) {
                        expandSelection(coords)
                    }
                    if (unselectedCount == mines) {
                       allBombsFound = true
                       flagsRemaining = 0
                    }
                    true
                }
            }
            CellState.SELECTED -> throw IllegalArgumentException("Cell already previously selected")
            CellState.FLAGGED -> true
        }
    }

    private fun expandSelection(coords: Pair<Int, Int>) {
        getNeighbours(coords).forEach { (r, c) ->
            val neighbour = board[r][c]
            if (neighbour.state == CellState.UNSELECTED && !neighbour.isMine) {
                board[r][c] = neighbour.copy(state = CellState.SELECTED)
                unselectedCount--
                if (neighbour.neighbourBombs == 0) {
                    expandSelection(neighbour.coord)
                }
            }
        }
    }

    fun reset() {
        flagsRemaining = mines
        allBombsFound = false
        unselectedCount = this.rows * this.cols
        for (row in 0 until this.rows) {
            for (col in 0 until this.cols) {
                board[row][col] = Cell(row to col)
            }
        }
        generateMineField()
    }
}
