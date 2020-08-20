package com.sample

import androidx.compose.Composable
import androidx.ui.foundation.Text
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
