package com.sample


class SolutionSettingsIdeaImpl(
    val commonImpl: SolutionSettingsImpl,
    val authIdea: SolutionAuthIdeaApi,
    val bonus: SolutionBonusIdeaApi,
    val ab: SolutionAbIdeaApi
) : SolutionSettingsIdeaApi {

    override fun renderSettings(builder: IdeaPanelBuilder) {
        builder.apply {
            authIdea.renderLoginForm(this)
            bonus.renderBonusesAndRefillButton(this)
            if (commonImpl.isDeveloperSettingsAvailable()) {
                ab.renderAbSettings(this)
            } else {
                button("Debug settings") {
                    commonImpl.actionOpenDeveloperSettings()
                }
            }
        }
    }

}
