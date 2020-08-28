package com.sample.compose

import androidx.compose.foundation.Box
import androidx.compose.foundation.drawBorder
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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