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

class SolutionWeatherAndroidImpl(
    val commonImpl: SolutionWeatherImpl
) : SolutionWeatherAndroidApi {

    @Composable
    override fun renderWeather() {
        WrapColorBox(color = commonImpl.getColor()) {
            Text(commonImpl.getWeatherString())
        }
    }

}
