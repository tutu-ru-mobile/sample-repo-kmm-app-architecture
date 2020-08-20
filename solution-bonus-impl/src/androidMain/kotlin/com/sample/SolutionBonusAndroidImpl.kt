package com.sample

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.material.Button

class SolutionBonusAndroidImpl(
    val common: SolutionBonusImpl
) : SolutionBonusApiAndroid {

    @Composable
    override fun renderBonusesAndRefillButton() {
        wrapColorBox(common.color, common.isAvailable()) {
            Column {
                _renderBonusCount()
                renderRefillButton()
            }
        }
    }

    @Composable
    override fun renderBonusCount() {
        wrapColorBox(common.color, common.isAvailable()) {
            _renderBonusCount()
        }
    }

    @Composable
    private fun _renderBonusCount() {
        if (common.isAvailable()) {
            Text("У вас ${common.store.stateFlow.value.bonusAmount} бонусов")
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

