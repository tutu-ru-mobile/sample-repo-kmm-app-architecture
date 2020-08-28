package com.sample

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.sample.compose.CheckBoxWithLabel
import com.sample.compose.WrapColorBox

class SolutionAbAndroidImpl(
    val commonImpl: SolutionAbImpl
) : SolutionAbAndroidApi {

    @Composable
    override fun renderAbSettings() {
        val state = commonImpl.store.state
        WrapColorBox(color = commonImpl.getColor()) {
            Column() {
                Text("feature toggles:")
                state.booleanToggles.forEach { toggle ->
                    CheckBoxWithLabel(toggle.key, toggle.value) {
                        commonImpl.store.send(
                            SolutionAbImpl.Action.SwitchBooleanAb(toggle.key)
                        )
                    }
                }
            }
        }
    }

}
