package com.sample

class SolutionAttentionConsoleImpl(
    val commonImpl: SolutionAttentionImpl,
    val solutionAuth: SolutionAuthApi,
    val weatherConsole: SolutionWeatherConsoleApi,
    val orderConsole: SolutionOrderConsoleApi,
    val solutionBonusConsole: SolutionBonusConsoleApi,
    val ab: SolutionAbApi
) : SolutionAttentionConsoleApi {

    override fun renderMainScreenAttention(builder: ConsolePanelBuilder) {
        builder.apply {
            solutionBonusConsole.renderBonusCount(this)
            if (solutionAuth.isAuthorized()) {
                orderConsole.renderNearestOrder(this)
            } else {
                weatherConsole.renderWeather(this)
            }
        }
    }

}
