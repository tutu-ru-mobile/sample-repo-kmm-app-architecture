package com.sample

class SolutionSearchFormConsoleImpl(
    val commonImpl: SolutionSearchFormImpl,
    val attentionConsole: SolutionAttentionConsoleApi
) : SolutionSearchFormConsoleApi {

    override fun renderSearchForm(builder: ConsolePanelBuilder) {
        builder.apply {
            row {
                spacer()
                title("Solution architecture sample")
            }
            spacer()
            attentionConsole.renderMainScreenAttention(this)
            spacer()
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
            spacer()
            button("Начать поиск") {
                commonImpl.store.send(SolutionSearchFormImpl.Action.Search)
            }
        }
    }

}

