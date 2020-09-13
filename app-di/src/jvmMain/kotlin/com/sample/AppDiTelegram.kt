package com.sample

class AppDiTelegram(private val common: AppDi) {
    val solutionTabsTelegram by lazy {
        SolutionTabsTelegramImpl(common.solutionTabs, searchTabTelegram)
    }

    val searchTabTelegram by lazy {
        SolutionTabSearchTelegramImpl(common.solutionTabSearch)
    }

}
