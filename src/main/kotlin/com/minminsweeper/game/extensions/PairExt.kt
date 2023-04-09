package com.minminsweeper.game.extensions

fun Pair<Int, Int>.moveUp(): Pair<Int, Int> {
    return Pair(this.first, this.second + 1)
}
