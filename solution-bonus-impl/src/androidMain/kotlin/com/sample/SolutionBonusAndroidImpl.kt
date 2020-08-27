package com.sample

import androidx.compose.foundation.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.sample.compose.CheckBoxWithLabel
import com.sample.compose.WrapColorBox

class SolutionBonusAndroidImpl(
    val common: SolutionBonusImpl
) : SolutionBonusApiAndroid {

    @Composable
    override fun renderBonusesAndRefillButton() {
        WrapColorBox(common.getColor(), common.isAvailable()) {
            Column {
                _renderBonusCount()
                renderRefillButton()
            }
        }
    }

    @Composable
    override fun renderBonusCount() {
        WrapColorBox(common.getColor(), common.isAvailable()) {
            _renderBonusCount()
        }
    }

    @Composable
    override fun renderBonusToggle() {
        WrapColorBox(common.getColor(), common.isAvailable()) {
            Column {
                _renderBonusCheckbox()
                if(common.canBuyWithBonus()) {
                    _renderBonusCount()
                    _renderBonusRules()
                }
            }
        }
    }

    @Composable
    private fun _renderBonusCount() {
        if (common.isAvailable()) {
            Text("У вас ${common.store.stateFlow.value.bonusAmount} бонусов")
        }
    }

    @Composable
    private fun _renderBonusRules() {
        if (common.isAvailable()) {
            Text(common.getBonusRules())
        }
    }

    @Composable
    private fun _renderBonusCheckbox() {
        if (common.isAvailable()) {
            CheckBoxWithLabel("использовать бонусы", common.canBuyWithBonus()) {
                common.store.send(SolutionBonusImpl.Action.SwitchBuyToggle())
            }
        }
    }

    @Composable
    fun renderRefillButton() {
        if (common.isAvailable()) {
            Button(onClick = {
                common.store.send(SolutionBonusImpl.Action.Add(1000))
            }) {
                Text("Добавить бонусы")
            }
        }
    }

}

