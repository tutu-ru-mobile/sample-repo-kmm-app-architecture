package com.sample

import kotlinx.coroutines.flow.StateFlow

@Suppress("unused")
fun getStateFlowAndRender(
    getGithubMail: ((String) -> Unit) -> Unit
): StateFlowAndRender<AppDi.GlobalState> {
    val di = AppDiIdea()
    return object : StateFlowAndRender<AppDi.GlobalState> {
        override val stateFlow: StateFlow<AppDi.GlobalState> =
            di.common.globalStateFlow

        override fun render(state: AppDi.GlobalState): IdeaPanel =
            di.solutionTabsIdea.renderScaffold()

    }
}
