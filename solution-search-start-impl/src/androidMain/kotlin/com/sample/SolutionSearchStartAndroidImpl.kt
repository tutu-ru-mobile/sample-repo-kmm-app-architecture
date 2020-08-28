package com.sample

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable

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
