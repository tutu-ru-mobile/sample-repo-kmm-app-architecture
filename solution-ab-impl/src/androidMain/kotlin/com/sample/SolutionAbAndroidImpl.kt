package com.sample

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import com.sample.compose.CheckBoxWithLabel

class SolutionAbAndroidImpl(
    val common: SolutionAbImpl
) : SolutionAbAndroidApi {

    @Composable
    override fun renderAbSettings() {
        val state = common.store.state
        Column() {
            Text("A/B features:")
            state.booleanToggles.forEach { toggle ->
                CheckBoxWithLabel(toggle.key, toggle.value) {
                    common.store.send(
                        SolutionAbImpl.Action.SwitchBooleanAb(toggle.key)
                    )
                }
            }
        }
    }

}
