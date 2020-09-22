package com.sample

import react.RBuilder
import react.dom.div

class SolutionAttentionBrowserImpl(
    val commonImpl: SolutionAttentionImpl,
    val solutionAuth: SolutionAuthApi,
    val weatherBrowser: SolutionWeatherBrowserApi,
    val orderBrowser: SolutionOrderBrowserApi,
    val solutionBonusBrowser: SolutionBonusBrowserApi,
    val ab: SolutionAbApi
) : SolutionAttentionBrowserApi {

    override fun renderMainScreenAttention(react: RBuilder) {
        react.apply {
            div {
                solutionBonusBrowser.renderBonusCount(this)
            }
            div {
                if (solutionAuth.isAuthorized()) {
                    orderBrowser.renderNearestOrder(this)
                } else {
                    weatherBrowser.renderWeather(this)
                }
            }
        }
    }

}
