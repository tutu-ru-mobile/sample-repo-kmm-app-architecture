package com.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

class BonusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bonusAndroid = SolutionBonusAndroidImpl(
            common = SolutionBonusImpl(
                solutionAb = SolutionAbStub()
            )
        )

        setContent {
            val state by bonusAndroid.common.store.stateFlow.collectAsState()
            println("state: $state")//не удалять, это костыль чтобы обновлось View при изменениие State
            val fontStyle = TextStyle(fontSize = 20.sp)
            MaterialTheme(
                typography = Typography(body1 = fontStyle, button = fontStyle)
            ) {
                Column {
                    bonusAndroid.renderRefillButton()
                    bonusAndroid.renderBonusesAndRefillButton()
                    bonusAndroid.renderBonusToggle()
                }
            }
        }
    }
}

class SolutionAbStub():SolutionAbApi {
    override fun registerBooleanToggle(key: String, defaultValue: Boolean) = Unit
    override fun getBooleanToggleState(key: String): Boolean = true
}
