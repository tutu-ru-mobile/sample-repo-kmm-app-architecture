package com.sample

import kotlinx.coroutines.flow.StateFlow

@Suppress("unused")
fun getStateFlowAndRender(
    getGithubMail: ((String) -> Unit) -> Unit
): StateFlowAndRender<GlobalState> {
    val di = AppDiIdea()
    return object : StateFlowAndRender<GlobalState> {
        override val stateFlow: StateFlow<GlobalState> =
            di.common.globalStateFlow

        override fun render(state: GlobalState): IdeaPanel =
            di.solutionTabsIdea.renderScaffold()

    }
}
