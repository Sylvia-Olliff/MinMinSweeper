package com.minminsweeper.game.board

class GameGrid(
    rows: Int,
    cols: Int,
    mines: Int
): Grid(rows, cols, mines) {
    init {
        generateMineField()
    }

    override fun generateMineField() {
        var mineCount = 0
        while (mineCount < mines) {
            var (row, col)= Pair((0 until rows).random(), (0 until cols).random())
            if (board[row][col].isMine) continue
            board[row][col] = board[row][col].copy(neighbourBombs = -1)
            generateNeighbourCount(row to col)
            mineCount++
        }
    }
}