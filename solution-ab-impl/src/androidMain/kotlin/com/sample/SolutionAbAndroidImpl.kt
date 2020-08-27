package com.sample

import androidx.compose.foundation.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
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
