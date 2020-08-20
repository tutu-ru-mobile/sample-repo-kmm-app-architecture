package com.sample

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.material.Button
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

