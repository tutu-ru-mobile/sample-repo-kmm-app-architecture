package com.sample.compose

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.layout.fillMaxWidth

@Composable
fun Central(modifier: Modifier = Modifier, lambda: @Composable Any.() -> Unit) {
    Box(
        modifier = modifier + Modifier.fillMaxWidth(),
        gravity = ContentGravity.Center
    ) {
        Unit.lambda()
    }
}
