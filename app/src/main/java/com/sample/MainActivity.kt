package com.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.collectAsState
import androidx.compose.getValue
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Typography
import androidx.ui.text.TextStyle
import androidx.ui.unit.sp
import com.sample.AppDi
import com.sample.AppDiAndroid

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val di = AppDi()
        val diAndroid = AppDiAndroid(di)
        setContent {
            val fonteStyle = TextStyle(fontSize = 20.sp)
            MaterialTheme(typography = Typography(body1 = fonteStyle, button = fonteStyle)
            ) {
                val state by di.globalStateFlow.collectAsState()
                println("actionCount: $state")
                diAndroid.mainNavAndroid.renderScaffold()
            }
        }
    }
}
