package com.sample.compose

import androidx.compose.foundation.Box
import androidx.compose.foundation.ClickableText
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString

@Composable
fun CheckBoxWithLabel(label: String, checked: Boolean, callback: () -> Unit) {
    Row() {
        Checkbox(checked = checked,
            onCheckedChange = {
                callback()
            }
        )
        ClickableText(
            text = AnnotatedString(label),
            onClick = {
                callback()
            })
    }
}
