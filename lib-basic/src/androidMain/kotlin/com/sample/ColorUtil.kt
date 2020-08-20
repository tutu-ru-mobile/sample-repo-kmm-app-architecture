package com.sample

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.drawBorder
import androidx.ui.graphics.Color
import androidx.ui.layout.padding
import androidx.ui.unit.dp

fun HexColor.toComposeColor() = Color(hexColor)

@Composable
fun wrapColorBox(
    color: HexColor,
    availabe: Boolean = true,
    lambda: @Composable Any.() -> Unit
) {
    Box(
        Modifier.drawBorder(
            2.dp,
            (if (availabe) color else HexColor(0x00_00_00_00)).toComposeColor()
        ) +
                Modifier.padding(16.dp)
    ) {
        Unit.lambda()
    }
}
