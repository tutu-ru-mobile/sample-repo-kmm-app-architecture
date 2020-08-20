package com.sample.compose

import androidx.compose.Composable
import androidx.ui.foundation.ClickableText
import androidx.ui.layout.Row
import androidx.ui.material.Checkbox
import androidx.ui.text.AnnotatedString

@Composable
    fun CheckBoxWithLabel(label: String, checked: Boolean, callback:()->Unit) {
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