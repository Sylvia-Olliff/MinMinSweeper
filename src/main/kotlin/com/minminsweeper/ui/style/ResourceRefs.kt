package com.minminsweeper.ui.style

import androidx.compose.ui.unit.dp

const val WINDOW_TITLE = "MinMinSweeper"

// General dimensions
val tinyPad = 2.dp
val smallPad = 10.dp
val windowPad = 15.dp
val headerHeight = 30.dp
const val BEVEL_STROKE_SMALL = 2.2f
const val BEVEL_STROKE_LARGE = 3f
val rulesWidth = headerHeight * 3
val dialogSize = 350.dp
val inputSize = 50.dp

// Menu
const val OPTIONS_MENU = "Options"
const val GAME_MENU = "New Game"
const val RULES_MENU = "Rules"

// NewGame
const val NG_HEADER_HEIGHT = "Height"
const val NG_HEADER_WIDTH = "Width"
const val NG_HEADER_MINES = "Mines"
const val LEVEL_CUSTOM = "CUSTOM"
const val START_GAME = "NEW GAME"
const val HEIGHT_ERROR_TEXT = "Height must be between 9 and 30"
const val WIDTH_ERROR_TEXT = "Width must be between 9 and 50"
const val MINES_ERROR_TEXT = "Mines must be between 5 and"

// Rules
const val FACE_CLICK = "Start a new game"
const val LEFT_SCREEN = "Flags available"
const val RIGHT_SCREEN = "Seconds passed"
const val NUMBER_CELL = "Count of nearby mines"
const val LEFT_CLICK = "Reveal"
const val RIGHT_CLICK = "Flag/Unflag"
const val LEFT_CLICK_DESCRIPTION = "Left mouse click animation"
const val RIGHT_CLICK_DESCRIPTION = "Right mouse click animation"

// TimeLimitDialog
const val TIME_OUT = "Time has run out!"

// ResetButton
const val RESET_DEFAULT_ICON = "face_default.svg"
const val RESET_DEFAULT_DESCRIPTION = "smiling face"
const val RESET_LOST_ICON = "face_lost.svg"
const val RESET_LOST_DESCRIPTION = "sad face"
const val RESET_WON_ICON = "face_won.svg"
const val RESET_WON_DESCRIPTION = "happy face with sunglasses"

// DigitalScreen
const val DIGITAL_TEST_TAG = "digital screen"

// Cell
val cellSize = 20.dp
val cellPadding = 3.dp
const val FLAG_ICON = "flag.svg"
const val FLAG_DESCRIPTION = "tiny red flag"
const val MINE_ICON = "mine.svg"
const val MINE_DESCRIPTION = "tiny mine"
const val MINE_X_ICON = "mine_x.svg"
const val MINE_X_DESCRIPTION = "crossed-out mine"

// Grid
const val GRID_TEST_TAG = "grid column"
