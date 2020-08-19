package com.sample

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.material.Button

class SolutionBonusAndroidImpl(
    val solutionBonus: SolutionBonusImpl
) : SolutionBonusApiAndroid {

    @Composable
    override fun renderBonusesAndRefillButton() {
        Column {
            renderBonusCount()
            renderRefillButton()
        }
    }

    @Composable
    override fun renderBonusCount() {
        Text("У вас ${solutionBonus.store.stateFlow.value.bonusAmount} бонусов")
    }

    @Composable
    override fun renderRefillButton() {
        Button(onClick = {
            solutionBonus.store.send(SolutionBonusImpl.Action.Add(1000))
        }) {
            Text("Добавить бонусы")
        }
    }

}

