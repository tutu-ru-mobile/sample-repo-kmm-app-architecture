
package com.sample

class SolutionWeatherIdeaImpl(
    val commonImpl: SolutionWeatherImpl
) : SolutionWeatherIdeaApi {

    override fun renderWeather(builder: IdeaPanelBuilder) {
        builder.apply {
            label(commonImpl.getWeatherString())
        }
    }

}
