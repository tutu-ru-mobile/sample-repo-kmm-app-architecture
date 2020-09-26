package com.sample

class SolutionAbIdeaImpl(
    val commonImpl: SolutionAbImpl
) : SolutionAbIdeaApi {

    override fun renderAbSettings(builder: IdeaPanelBuilder) {
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
