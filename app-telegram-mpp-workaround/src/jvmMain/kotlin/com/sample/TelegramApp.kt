package com.sample


import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.telegramError
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.KeyboardReplyMarkup
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton
import com.github.kotlintelegrambot.logging.LogLevel
import kotlinx.coroutines.flow.collectLatest

val CHAT_ID = 185159406L

fun runTelegramApp(telegramBotToken: String) {
    println(MyColors.BLUE)
    val di = AppDi()
    val diTelegram = AppDiTelegram(di)

    var textCallbacks: List<(text: String) -> Unit> = emptyList()//todo thread safe
    var dataCallbacks: List<(data: String) -> Unit> = emptyList()

    fun clearCallbacks() {
        textCallbacks = emptyList()
        dataCallbacks = emptyList()
    }

    val bot = bot {
        token = telegramBotToken
        timeout = 30
        logLevel = LogLevel.Network.None//Body

        dispatch {
            callbackQuery { bot, update ->
                update.callbackQuery?.let {
                    it.data
                }
            }

            text(/* may set text */) { bot, update ->
                textCallbacks.forEach { textCallback ->
                    update.message?.text?.let {
                        textCallback(it)
                    }
                }
//                println("handle text: update.message?.text: ${update.message?.text}, chatId: ${update.message?.chat?.id}")
            }

            telegramError { _, telegramError ->
                println("TELEGRAM ERROR: ${telegramError.getErrorMessage()}")
            }
        }
    }

    bot.startPolling()

    launchCoroutineDirty {
        di.globalStateFlow.collectLatest { state ->
            clearCallbacks()
            val scaffold = diTelegram.solutionTabsTelegram.renderScaffold()

            when (val content = scaffold.content) {
                is Content.Message -> {
                    bot.sendMessage(
                        CHAT_ID, text = content.text
                    )
                }
                is Content.Button -> {
                    bot.sendMessage(
                        CHAT_ID, text = "todo buttons"
                    )
                }
                is Content.Container -> {
                    bot.sendMessage(
                        CHAT_ID, text = "todo container"
                    )
                }
            }

            bot.sendMessage(
                chatId = CHAT_ID,
                text = "Navigation buttons",
                replyMarkup = KeyboardReplyMarkup(
                    keyboard = listOf(
                        scaffold.navButtons.map { btn ->
                            textCallbacks += {
                                if (it == btn.text) {
                                    btn.onClick()
                                }
                            }
                            KeyboardButton(
                                text = btn.text
                            )
                        }
                    ), resizeKeyboard = true
                )
            )
        }
    }
//    runBot(telegramBotToken)
}
