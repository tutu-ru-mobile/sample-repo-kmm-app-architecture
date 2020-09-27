package com.sample

class SolutionSearchStartConsoleImpl(
    val common: SolutionSearchStartImpl
) : SolutionSearchStartConsoleApi {

    override fun renderSearching(builder: ConsolePanelBuilder) {
        builder.apply {
            row {
                spacer()
                title("Searching ${common.store.state.searchQuery}...")
            }
        }
    }

}
