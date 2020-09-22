package com.sample

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

typealias ViewFlow = StateFlow<IdeaPanel>

fun <S> StateFlowAndRender<S>.toViewFlow(): ViewFlow {
    val state = stateFlow.value
    val result = MutableStateFlow(render(state))
    MainScope().launch {
        stateFlow.collectLatest {
            result.value = render(it)
        }
    }
    return result
}

fun getStateFlowAndRenderAdapter() =
    getStateFlowAndRender { callback ->
        getGithubMail { mail ->
            callback(mail)
        }
    }.toViewFlow()
