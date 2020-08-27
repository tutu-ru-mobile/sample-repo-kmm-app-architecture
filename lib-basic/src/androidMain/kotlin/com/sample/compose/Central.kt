package com.sample.compose

import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Central(modifier: Modifier = Modifier, lambda: @Composable Any.() -> Unit) {
    Box(
        modifier = modifier + Modifier.fillMaxWidth(),
        gravity = ContentGravity.Center
    ) {
        Unit.lambda()
    }
}
