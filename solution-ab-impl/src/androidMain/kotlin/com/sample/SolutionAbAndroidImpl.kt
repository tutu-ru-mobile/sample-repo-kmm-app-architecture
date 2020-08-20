package com.sample

import androidx.compose.Composable
import androidx.ui.foundation.ClickableText
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.material.Checkbox
import androidx.ui.text.AnnotatedString

class SolutionAbAndroidImpl(
    val common: SolutionAbImpl
) : SolutionAbAndroidApi {

    @Composable
    override fun renderAbSettings() {
        val state = common.store.state
        Column() {
            Text("A/B features:")
            state.booleanToggles.forEach { toggle ->

                Row() {
                    Checkbox(checked = toggle.value,
                        onCheckedChange = {
                            common.store.send(
                                SolutionAbImpl.Action.SwitchBooleanAb(toggle.key)
                            )
                        }
                    )
                    ClickableText(
                        text = AnnotatedString(toggle.key),
                        onClick = {
                            common.store.send(
                                SolutionAbImpl.Action.SwitchBooleanAb(toggle.key)
                            )
                        })
                }
            }
        }
    }

}

