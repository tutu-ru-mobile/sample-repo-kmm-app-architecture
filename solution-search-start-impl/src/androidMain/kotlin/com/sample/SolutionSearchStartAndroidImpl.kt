package com.sample

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.material.Button

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
