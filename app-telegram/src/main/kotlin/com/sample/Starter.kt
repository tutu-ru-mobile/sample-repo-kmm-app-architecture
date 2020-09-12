package com.sample

fun todo() {

}

fun main() {
    println(MyColors.BLUE)
    val di = AppDi()
    val diTelegram = AppDiTelegram(di)
//    setContent {
//        val state by di.globalStateFlow.collectAsState()
//        println("actionCount: $state")//не удалять, это костыль чтобы обновлось View при изменениие State
//        diTelegram.solutionTabsTelegram.renderScaffold()
//
//    }
    runBot()
}
