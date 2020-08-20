package com.sample

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.material.FilledTextField
import androidx.ui.unit.dp


class SolutionSearchFormAndroidImpl(
    val common: SolutionSearchFormImpl,
    val attentionAndroid: SolutionAttentionAndroidApi
) : SolutionSearchFormAndroidApi {

    @Composable
    override fun renderSearchForm() {
        Column {
            central {
                Box(
                    modifier = Modifier.padding(16.dp),
                    backgroundColor = common.attentionBackgroundColor.toComposeColor()
                ) {
                    attentionAndroid.renderMainScreenAttention()
                }
            }

            FilledTextField(
                value = common.store.state.searchFrom,
                onValueChange = {
                    common.store.send(SolutionSearchFormImpl.Action.From(it))
                },
                label = { Text("Откуда") },
                modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
            )
            FilledTextField(
                value = common.store.state.searchTo,
                onValueChange = {
                    common.store.send(SolutionSearchFormImpl.Action.To(it))
                },
                label = { Text("Куда") },
                modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
            )
            Button(
                modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth(),
                onClick = {
                common.store.send(SolutionSearchFormImpl.Action.Search)
            }) {
                Text("Начать поиск")
            }
        }
    }


}
