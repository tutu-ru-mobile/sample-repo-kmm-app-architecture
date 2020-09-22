package com.sample

import react.RBuilder
import react.dom.h2

class SolutionSearchStartBrowserImpl(
    val common: SolutionSearchStartImpl
) : SolutionSearchStartBrowserApi {

    override fun renderSearching(react: RBuilder) {
        react.apply {
            h2 {
                +"Searching ${common.store.state.searchQuery}..."
            }
            btn("Complete search") {
                common.completeSearch()
            }
        }
    }

}
