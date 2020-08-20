package com.sample.compose

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.unit.dp

@Composable
fun DefaultPadding(modifier: Modifier = Modifier, lambda: @Composable Any.() -> Unit) {
    Box(
        modifier = modifier + Modifier.padding(16.dp),
        gravity = ContentGravity.Center
    ) {
        Unit.lambda()
    }
}
