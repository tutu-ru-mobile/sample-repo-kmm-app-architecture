package com.sample

class SolutionAbConsoleImpl(
    val commonImpl: SolutionAbImpl
) : SolutionAbConsoleApi {

    override fun renderAbSettings(builder: ConsolePanelBuilder) {
        builder.apply {
            val state = commonImpl.store.state
            state.booleanToggles.forEach { toggle ->
                row {
                    checkBox(toggle.key, toggle.value) {
                        commonImpl.store.send(
                            SolutionAbImpl.Action.SwitchBooleanAb(toggle.key)
                        )
                    }
                }
            }
        }
    }

}
