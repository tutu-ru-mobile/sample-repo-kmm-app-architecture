package com.sample

class SolutionAttentionIdeaImpl(
    val commonImpl: SolutionAttentionImpl,
    val solutionAuth: SolutionAuthApi,
    val weatherIdea: SolutionWeatherIdeaApi,
    val orderIdea: SolutionOrderIdeaApi,
    val solutionBonusIdea: SolutionBonusIdeaApi,
    val ab: SolutionAbApi
) : SolutionAttentionIdeaApi {

    override fun renderMainScreenAttention(builder: IdeaPanelBuilder) {
        builder.apply {
            solutionBonusIdea.renderBonusCount(this)
            if (solutionAuth.isAuthorized()) {
                orderIdea.renderNearestOrder(this)
            } else {
                weatherIdea.renderWeather(this)
            }
        }
    }

}
