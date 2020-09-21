package com.sample

class AppDiBrowser(val common: AppDi) {
    val solutionTabsBrowser by lazy {
        SolutionTabsBrowserImpl(
            common.solutionTabs
        )
    }
}
