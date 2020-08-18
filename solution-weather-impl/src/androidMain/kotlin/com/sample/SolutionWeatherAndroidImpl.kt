package com.sample

import androidx.compose.Composable
import androidx.ui.foundation.Text

class SolutionWeatherAndroidImpl(
    val common: SolutionWeatherImpl
) : SolutionWeatherAndroidApi {

    @Composable
    override fun renderWeather() {
        Text(common.getWeatherString())
    }


}

