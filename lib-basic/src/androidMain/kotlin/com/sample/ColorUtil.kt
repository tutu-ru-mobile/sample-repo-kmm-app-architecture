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
        modifier = Modifier.drawBorder(
            if(availabe) 2.dp else 0.dp,
            color.toComposeColor()
        ) + Modifier.padding(16.dp)
    ) {
        Unit.lambda()
    }
}
