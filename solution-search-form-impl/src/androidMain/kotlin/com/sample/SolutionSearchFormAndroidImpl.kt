package com.sample

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.material.FilledTextField
import androidx.ui.unit.dp
import com.sample.compose.Central
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

                FilledTextField(
                    value = commonImpl.store.state.searchFrom,
                    onValueChange = {
                        commonImpl.store.send(SolutionSearchFormImpl.Action.From(it))
                    },
                    label = { Text("Откуда") },
                    modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
                )
                FilledTextField(
                    value = commonImpl.store.state.searchTo,
                    onValueChange = {
                        commonImpl.store.send(SolutionSearchFormImpl.Action.To(it))
                    },
                    label = { Text("Куда") },
                    modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
                )
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
