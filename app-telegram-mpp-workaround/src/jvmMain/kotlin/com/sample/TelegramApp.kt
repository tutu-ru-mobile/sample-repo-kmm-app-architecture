package com.sample

fun runTelegramApp(telegramBotToken: String) {
    println(MyColors.BLUE)
    val di = AppDi()
    val diTelegram = AppDiTelegram(di)
    diTelegram.solutionTabsTelegram
//    setContent {
//        val state by di.globalStateFlow.collectAsState()
//        println("actionCount: $state")//не удалять, это костыль чтобы обновлось View при изменениие State
//        diTelegram.solutionTabsTelegram.renderScaffold()
//
//    }
    runBot(telegramBotToken)
}
