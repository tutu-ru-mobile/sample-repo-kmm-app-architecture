package com.sample

class SolutionWeatherConsoleImpl(
    val commonImpl: SolutionWeatherImpl
) : SolutionWeatherConsoleApi {

    override fun renderWeather(builder: ConsolePanelBuilder) {
        builder.apply {
            label(commonImpl.getWeatherString())
        }
    }

}
