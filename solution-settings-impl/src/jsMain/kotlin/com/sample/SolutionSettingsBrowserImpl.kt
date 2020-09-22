package com.sample

import react.RBuilder
import react.dom.br
import react.dom.h2

class SolutionSettingsBrowserImpl(
    val commonImpl: SolutionSettingsImpl,
    val authBrowser: SolutionAuthBrowserApi,
    val bonus: SolutionBonusBrowserApi,
    val ab: SolutionAbBrowserApi
) : SolutionSettingsBrowserApi {

    override fun renderSettings(react: RBuilder) {
        react.apply {
            authBrowser.renderLoginForm(this)
            br {}
            bonus.renderBonusesAndRefillButton(this)
            if (commonImpl.isDeveloperSettingsAvailable()) {
                ab.renderAbSettings(this)
            } else {
                btn("Debug settings") {
                    commonImpl.actionOpenDeveloperSettings()
                }
            }
        }
    }

}
