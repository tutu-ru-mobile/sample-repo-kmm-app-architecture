package com.sample

class SolutionSearchFormIdeaImpl(
    val commonImpl: SolutionSearchFormImpl,
    val attentionIdea: SolutionAttentionIdeaApi
) : SolutionSearchFormIdeaApi {

    override fun renderSearchForm(builder: IdeaPanelBuilder) {
        builder.apply {
            attentionIdea.renderMainScreenAttention(this)
            val state = commonImpl.store.state
            row {
                label("Откуда")
                textInput(state.searchFrom) {
                    commonImpl.store.send(SolutionSearchFormImpl.Action.From(it))
                }
            }
            row {
                label("Куда")
                textInput(state.searchTo) {
                    commonImpl.store.send(SolutionSearchFormImpl.Action.To(it))
                }
            }
            button("Начать поиск") {
                commonImpl.store.send(SolutionSearchFormImpl.Action.Search)
            }
        }
    }

}

