package com.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.collectAsState
import androidx.compose.getValue
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Typography
import androidx.ui.text.TextStyle
import androidx.ui.unit.dp
import androidx.ui.unit.sp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val di = AppDi()
        val diAndroid = AppDiAndroid(di)
        setContent {
            val state by di.globalStateFlow.collectAsState()
            val fontStyle = TextStyle(fontSize = 20.sp)
            MaterialTheme(typography = Typography(body1 = fontStyle, button = fontStyle)
            ) {
                 println("actionCount: $state")//todo не удалять, это костыль чтобы обновлось View при изменениие State
                diAndroid.solutionTabsAndroid.renderScaffold()
            }
        }
    }
}
