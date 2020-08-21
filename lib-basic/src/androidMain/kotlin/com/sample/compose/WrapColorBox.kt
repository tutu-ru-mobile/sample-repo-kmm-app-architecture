package com.sample.compose

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.drawBorder
import androidx.ui.layout.padding
import androidx.ui.unit.dp
import com.sample.HexColor
import com.sample.toComposeColor

@Composable
fun WrapColorBox(
    color: HexColor,
    availabe: Boolean = true,
    lambda: @Composable Any.() -> Unit
) {
    Box(
        Modifier.drawBorder(
            4.dp,
            (if (availabe) color else HexColor(0x00_00_00_00)).toComposeColor()
        ) +
                Modifier.padding(16.dp)
    ) {
        Unit.lambda()
    }
}