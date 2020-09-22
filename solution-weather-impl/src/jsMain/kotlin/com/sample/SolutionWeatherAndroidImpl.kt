package com.sample

import react.RBuilder
import react.dom.div

class SolutionWeatherBrowserImpl(
    val commonImpl: SolutionWeatherImpl
) : SolutionWeatherBrowserApi {

    override fun renderWeather(react: RBuilder) {
        react.apply {
            div {
                +commonImpl.getWeatherString()
            }
        }
    }

}
