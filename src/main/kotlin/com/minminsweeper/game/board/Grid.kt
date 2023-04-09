package com.minminsweeper.game.board

abstract class Grid(size: Pair<Int, Int>) {
    protected val cellMap: List<List<Cell>>

    init {
        val initialList = mutableListOf<MutableList<Cell>>()
        for (x in 1..size.first) {
            val row = mutableListOf<Cell>()
            for (y in 1..size.second) {
                row.add(Cell(
                    coord = Pair(x, y),
                    isBomb = false,
                    digits = null,
                    isChecked = false
                ))
            }
            initialList.add(row)
        }
        cellMap = initialList.toList()
    }
}
