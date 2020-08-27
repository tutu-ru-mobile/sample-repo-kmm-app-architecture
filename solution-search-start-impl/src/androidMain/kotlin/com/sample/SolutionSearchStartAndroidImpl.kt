package com.sample

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.sample.compose.Central
import com.sample.compose.CheckBoxWithLabel
import com.sample.compose.WrapColorBox

class SolutionSearchStartAndroidImpl(
    val common: SolutionSearchStartImpl
) : SolutionSearchStartAndroidApi {

    @Composable
    override fun renderSearching() {
        Column {
            Text("Searching ${common.store.state.searchQuery}...")
            Button(onClick = {
                common.completeSearch()
            }) {
                Text("Complete search")
            }
        }
    }

}
