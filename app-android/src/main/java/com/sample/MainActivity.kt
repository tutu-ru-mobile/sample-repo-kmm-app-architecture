package com.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val di = AppDi()
        val diAndroid = AppDiAndroid(di)
        setContent {
            val state by di.globalStateFlow.collectAsState()
            println("actionCount: $state")//не удалять, это костыль чтобы обновлось View при изменениие State

            val fontStyle = TextStyle(fontSize = 20.sp)
            MaterialTheme(
                typography = Typography(body1 = fontStyle, button = fontStyle)
            ) {
                diAndroid.solutionTabsAndroid.renderScaffold()
            }
        }
    }
}
