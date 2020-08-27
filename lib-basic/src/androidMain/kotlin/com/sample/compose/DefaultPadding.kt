package com.sample.compose

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Box
import androidx.compose.foundation.ClickableText
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString


@Composable
fun DefaultPadding(modifier: Modifier = Modifier, lambda: @Composable Any.() -> Unit) {
    Box(
        modifier = modifier + Modifier.padding(16.dp),
        gravity = ContentGravity.Center
    ) {
        Unit.lambda()
    }
}
