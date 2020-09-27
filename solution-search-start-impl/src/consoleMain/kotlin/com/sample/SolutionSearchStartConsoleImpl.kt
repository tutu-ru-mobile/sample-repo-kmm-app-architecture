package com.sample

class SolutionSearchStartConsoleImpl(
    val common: SolutionSearchStartImpl
) : SolutionSearchStartConsoleApi {

    override fun renderSearching(builder: ConsolePanelBuilder) {
        builder.apply {
            title("Searching ${common.store.state.searchQuery}...")
            button("Complete search") {
                common.completeSearch()
            }
        }
    }

}
