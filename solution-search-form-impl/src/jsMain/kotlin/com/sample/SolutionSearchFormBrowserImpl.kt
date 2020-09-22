package com.sample

import react.RBuilder
import react.dom.div

class SolutionSearchFormBrowserImpl(
    val commonImpl: SolutionSearchFormImpl,
    val attentionBrowser: SolutionAttentionBrowserApi
) : SolutionSearchFormBrowserApi {

    override fun renderSearchForm(react: RBuilder) {
        react.apply {
            div {
                attentionBrowser.renderMainScreenAttention(this)
            }
            inp("Откуда", commonImpl.store.state.searchFrom) {
                commonImpl.store.send(SolutionSearchFormImpl.Action.From(it))
            }
            inp("Куда", commonImpl.store.state.searchTo) {
                commonImpl.store.send(SolutionSearchFormImpl.Action.To(it))
            }
            btn("Начать поиск") {
                commonImpl.store.send(SolutionSearchFormImpl.Action.Search)
            }
        }
    }

}
