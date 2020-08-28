package com.sample

import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
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
