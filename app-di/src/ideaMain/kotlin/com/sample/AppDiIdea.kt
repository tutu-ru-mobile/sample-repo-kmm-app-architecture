package com.sample

class AppDiIdea(
    val common: AppDi
) {
    val solutionTabsIdea by lazy {
        SolutionTabsIdeaImpl(
            commonImpl = common.solutionTabs
        )
    }

}
