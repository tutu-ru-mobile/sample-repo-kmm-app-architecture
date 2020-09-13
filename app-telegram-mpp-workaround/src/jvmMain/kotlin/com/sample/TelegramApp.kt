package com.sample


import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

val CHAT_ID = 185159406L

fun runTelegramApp(telegramBotToken: String) {
    println(MyColors.BLUE)
    val di = AppDi()
    val diTelegram = AppDiTelegram(di)
    launchCoroutineDirty {
        di.globalStateFlow.collectLatest {
            val scaffold = diTelegram.solutionTabsTelegram.renderScaffold()

        }
    }
//    setContent {
//        val state by di.globalStateFlow.collectAsState()
//        println("actionCount: $state")//не удалять, это костыль чтобы обновилось View при изменениие State
//        diTelegram.solutionTabsTelegram.renderScaffold()
//
//    }
    runBot(telegramBotToken)
}
