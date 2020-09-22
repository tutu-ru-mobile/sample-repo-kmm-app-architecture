package com.sample


import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.telegramError
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.KeyboardReplyMarkup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton
import com.github.kotlintelegrambot.entities.polls.PollType
import com.github.kotlintelegrambot.logging.LogLevel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlin.random.Random

val CHAT_ID = 185159406L

fun runTelegramApp(telegramBotToken: String) {
    val di = AppDi()

    var paymentPollHook: () -> Unit = {}
    var inputStrHook: (text: String, Callback: (String) -> Unit) -> Unit = { t, c -> }

    var textCallbacks: List<(text: String) -> Unit> = emptyList()//todo thread safe
    var dataCallbacks: List<(data: String) -> Unit> = emptyList()

    fun clearCallbacks() {
        textCallbacks = emptyList()
        dataCallbacks = emptyList()
    }

    val diTelegram = AppDiTelegram(
        di,
        paymentPoll = {
            paymentPollHook()
        },
        inputStr = { t, c ->
            inputStrHook(t, c)
        }
    )

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

            text(/* may set concrete text */) { bot, update ->
                val text = update.message?.text
                if (text != null) {
                    textCallbacks.forEach { it(text) }
                }
            }

            callbackQuery(/*  may set concrete data */) { bot, update ->
                val data = update.callbackQuery?.data
                if (data != null) {
                    dataCallbacks.forEach { it(data) }
                }
            }

            telegramError { _, telegramError ->
                println("TELEGRAM ERROR: ${telegramError.getErrorMessage()}")
            }
        }
    }

    bot.startPolling()

    launchAppScope {
        di.globalStateFlow.debounce(50L).collectLatest { state ->
            clearCallbacks()
            val scaffold = diTelegram.solutionTabsTelegram.renderScaffold()

            // Кнопки навигации снизу
            bot.sendMessage(
                chatId = CHAT_ID,
                text = List(45) { "." }.joinToString("\n"),
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

            // Контент старницы
            fun renderContent(content: TelegramView) {
                when (content) {
                    is TelegramView.Message -> {
                        bot.sendMessage(
                            CHAT_ID,
                            text = content.text,
                            replyMarkup = content.buttons?.map { horizontalGroup ->//todo maybe redundant nullable
                                horizontalGroup.map { btn ->
                                    val rand = Random.nextInt().toString()
                                    dataCallbacks += {
                                        if (it == rand) {
                                            btn.onClick()
                                        }
                                    }
                                    InlineKeyboardButton(
                                        text = btn.text,
                                        callbackData = rand
                                    )
                                }
                            }?.let { InlineKeyboardMarkup(it) }
                        )
                    }
                    is TelegramView.Container -> {
                        content.children.forEach {
                            renderContent(it)
                        }
                    }
                }
            }

            renderContent(scaffold.content)
        }
    }
    paymentPollHook = {
        bot.sendMessage(
            CHAT_ID,
            List(45) { "." }.joinToString("\n")
        )
        bot.sendPoll(
            chatId = CHAT_ID,
            type = PollType.QUIZ,
            question = "Ура! Вы приобрели билет. Довольны качеством сервиса?",
            options = listOf("Да, доволен", "Средне...", "Нет, не доволен"),
            correctOptionId = 0,
            isAnonymous = false
        )
    }
    inputStrHook = { text, callback ->
        bot.sendMessage(
            CHAT_ID,
            text
        )
        textCallbacks += {
            callback(it)
        }
    }
//    runBot(telegramBotToken)
}
