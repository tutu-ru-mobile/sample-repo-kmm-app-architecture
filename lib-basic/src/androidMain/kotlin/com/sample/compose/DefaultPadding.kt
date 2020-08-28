package com.sample.compose

import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DefaultPadding(modifier: Modifier = Modifier, lambda: @Composable Any.() -> Unit) {
    Box(
        modifier = modifier + Modifier.padding(16.dp),
        gravity = ContentGravity.Center
    ) {
        Unit.lambda()
    }
}
