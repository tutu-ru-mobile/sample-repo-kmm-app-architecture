package com.sample

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

                TextField(
                    value = commonImpl.store.state.searchFrom,
                    onValueChange = {
                        commonImpl.store.send(SolutionSearchFormImpl.Action.From(it))
                    },
                    label = { Text("Откуда") },
                    modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
                )
                TextField(
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
