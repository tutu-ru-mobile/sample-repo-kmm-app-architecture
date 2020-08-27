package com.sample.compose

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Box
import androidx.compose.foundation.ClickableText
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.drawBorder
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
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