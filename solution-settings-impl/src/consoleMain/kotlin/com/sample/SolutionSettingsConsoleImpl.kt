package com.sample


class SolutionSettingsConsoleImpl(
    val commonImpl: SolutionSettingsImpl,
    val authConsole: SolutionAuthConsoleApi,
    val bonus: SolutionBonusConsoleApi,
    val ab: SolutionAbConsoleApi
) : SolutionSettingsConsoleApi {

    override fun renderSettings(builder: ConsolePanelBuilder) {
        builder.apply {
            authConsole.renderLoginForm(this)
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
