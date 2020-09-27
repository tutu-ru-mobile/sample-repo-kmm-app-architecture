package com.sample

class SolutionSearchFormConsoleImpl(
    val commonImpl: SolutionSearchFormImpl,
    val attentionConsole: SolutionAttentionConsoleApi
) : SolutionSearchFormConsoleApi {

    override fun renderSearchForm(builder: ConsolePanelBuilder) {
        builder.apply {
            attentionConsole.renderMainScreenAttention(this)
            val state = commonImpl.store.state
            row {
                textInput("Откуда", state.searchFrom) {
                    commonImpl.store.send(SolutionSearchFormImpl.Action.From(it))
                }
            }
            row {
                textInput("Куда", state.searchTo) {
                    commonImpl.store.send(SolutionSearchFormImpl.Action.To(it))
                }
            }
            button("Начать поиск") {
                commonImpl.store.send(SolutionSearchFormImpl.Action.Search)
            }
        }
    }

}

