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


class SolutionSearchFormAndroidImpl(
    val commonImpl: SolutionSearchFormImpl,
    val attentionAndroid: SolutionAttentionAndroidApi
) : SolutionSearchFormAndroidApi {

    @Composable
    override fun renderSearchForm() {

        WrapColorBox(color = commonImpl.getColor()) {
            Column {
                Central {
                    Box(
                        modifier = Modifier.padding(16.dp),
                        backgroundColor = commonImpl.attentionBackgroundColor.toComposeColor()
                    ) {
                        attentionAndroid.renderMainScreenAttention()
                    }
                }

//TODO:
//                FilledTextField(
//                    value = commonImpl.store.state.searchFrom,
//                    onValueChange = {
//                        commonImpl.store.send(SolutionSearchFormImpl.Action.From(it))
//                    },
//                    label = { Text("Откуда") },
//                    modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
//                )
//                FilledTextField(
//                    value = commonImpl.store.state.searchTo,
//                    onValueChange = {
//                        commonImpl.store.send(SolutionSearchFormImpl.Action.To(it))
//                    },
//                    label = { Text("Куда") },
//                    modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
//                )

                Button(
                    modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth(),
                    onClick = {
                        commonImpl.store.send(SolutionSearchFormImpl.Action.Search)
                    }) {
                    Text("Начать поиск")
                }
            }
        }

    }

}
