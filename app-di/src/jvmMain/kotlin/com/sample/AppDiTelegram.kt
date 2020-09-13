package com.sample

class AppDiTelegram(val common: AppDi) {
    val solutionTabsTelegram by lazy {
        SolutionTabsTelegramImpl(common.solutionTabs)
    }

}
