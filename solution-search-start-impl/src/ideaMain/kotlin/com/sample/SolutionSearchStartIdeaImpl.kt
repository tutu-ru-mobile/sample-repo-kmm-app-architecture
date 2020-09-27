package com.sample

class SolutionSearchStartIdeaImpl(
    val common: SolutionSearchStartImpl
) : SolutionSearchStartIdeaApi {

    override fun renderSearching(builder: IdeaPanelBuilder) {
        builder.apply {
            title("Searching ${common.store.state.searchQuery}...")
        }
    }

}
