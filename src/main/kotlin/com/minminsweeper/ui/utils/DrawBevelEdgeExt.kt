package com.minminsweeper.ui.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.minminsweeper.ui.style.MinMinSweeperColors

fun DrawScope.drawBevelEdge(strokeWidth: Float, isElevated: Boolean = true) {
    if (isElevated) {
        val elevatedBevel = 1f
        drawLine(
            MinMinSweeperColors.tertiary,
            Offset(elevatedBevel, elevatedBevel),
            Offset(size.width - elevatedBevel, elevatedBevel),
            strokeWidth,
            StrokeCap.Square
        )
        drawLine(
            MinMinSweeperColors.tertiary,
            Offset(elevatedBevel, elevatedBevel),
            Offset(elevatedBevel, size.height - elevatedBevel),
            strokeWidth,
            StrokeCap.Square
        )
        drawLine(
            MinMinSweeperColors.onPrimary,
            Offset(size.width - elevatedBevel, elevatedBevel),
            Offset(size.width - elevatedBevel, size.height - elevatedBevel),
            strokeWidth,
            StrokeCap.Square
        )
        drawLine(
            MinMinSweeperColors.onPrimary,
            Offset(elevatedBevel, size.height - elevatedBevel),
            Offset(size.width - elevatedBevel, size.height - elevatedBevel),
            strokeWidth,
            StrokeCap.Square
        )
    } else {
        val sunkenBevel = -2f
        drawLine(
            MinMinSweeperColors.onPrimary,
            Offset(sunkenBevel, sunkenBevel),
            Offset(size.width - sunkenBevel, sunkenBevel),
            strokeWidth,
            StrokeCap.Square
        )
        drawLine(
            MinMinSweeperColors.onPrimary,
            Offset(sunkenBevel, sunkenBevel),
            Offset(sunkenBevel, size.height - sunkenBevel),
            strokeWidth,
            StrokeCap.Square
        )
        drawLine(
            MinMinSweeperColors.tertiary,
            Offset(size.width - sunkenBevel, sunkenBevel),
            Offset(size.width - sunkenBevel, size.height - sunkenBevel),
            strokeWidth,
            StrokeCap.Square
        )
        drawLine(
            MinMinSweeperColors.tertiary,
            Offset(sunkenBevel, size.height - sunkenBevel),
            Offset(size.width - sunkenBevel, size.height - sunkenBevel),
            strokeWidth,
            StrokeCap.Square
        )
    }
}
