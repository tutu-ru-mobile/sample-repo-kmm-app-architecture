package com.sample

import react.RBuilder
import react.dom.br
import react.dom.h2

class SolutionAbBrowserImpl(
    val commonImpl: SolutionAbImpl
) : SolutionAbBrowserApi {

    override fun renderAbSettings(react: RBuilder) {
        react.apply {
            val state = commonImpl.store.state
            h2 {
                +"feature toggles:"
            }
            state.booleanToggles.forEach { toggle ->
                br {}
                checkBox(toggle.key, toggle.value) {
                    commonImpl.send(SolutionAbImpl.Action.SwitchBooleanAb(toggle.key))
                }
            }
        }
    }

}
