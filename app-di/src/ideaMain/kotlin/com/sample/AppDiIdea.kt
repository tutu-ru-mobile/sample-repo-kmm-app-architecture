package com.sample

class AppDiIdea {
    val common = AppDi()
    val ordersTabIdea by lazy {
        SolutionOrderIdeaImpl(common.solutionOrder)
    }
    val solutionTabsIdea by lazy {
        SolutionTabsIdeaImpl(
            commonImpl = common.solutionTabs,
            ordersTabIdea = ordersTabIdea
        )
    }
}
