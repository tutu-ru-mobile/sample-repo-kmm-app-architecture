package com.sample

import kotlinx.coroutines.flow.StateFlow

sealed class Intent {
    object ChangeChecked : Intent()
    object OpenGithubAuth : Intent()
    class SuccessGithubAuth(val mail: String, val token: String) : Intent()
    class EditSearchText(val searchText: String) : Intent()
}

sealed class SideEffect {
    object OpenGithubAuth : SideEffect()
}

data class UniWindowState(
    val checked: Boolean = true,
    val searchText: String = "Москва",
    val githubMail: String? = null
)

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
