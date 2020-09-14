package com.sample

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.KeyboardReplyMarkup
import com.github.kotlintelegrambot.entities.ParseMode.MARKDOWN_V2
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton
import com.github.kotlintelegrambot.entities.polls.PollType.QUIZ
import com.github.kotlintelegrambot.logging.LogLevel

fun runBot(telegramBotToken: String) {

    val bot = bot {
        token = telegramBotToken
        timeout = 30
        logLevel = LogLevel.Network.Body

        dispatch {
            command("markdown") {
                val markdownV2Text = """
                    *bold \*text*
                    _italic \*text_
                    __underline__
                    ~strikethrough~
                    *bold _italic bold ~italic bold strikethrough~ __underline italic bold___ bold*
                    [inline URL](http://www.example.com/)
                    [inline mention of a user](tg://user?id=123456789)
                    `inline fixed-width code`
                    ```kotlin
                    fun main() {
                        println("Hello Kotlin!")
                    }
                    ```
                """.trimIndent()
                bot.sendMessage(
                    chatId = message.chat.id,
                    text = markdownV2Text,
                    parseMode = MARKDOWN_V2
                )
            }

            command("start") {
                val inlineKeyboardMarkup = InlineKeyboardMarkup(
                    listOf(
                        listOf(
                            InlineKeyboardButton(text = "a", callbackData = "ca")
                        )
                    )
                )
                bot.sendMessage(
                    chatId = message.chat.id,
                    text = "Hello, inline buttons!",
                    replyMarkup = inlineKeyboardMarkup
                )
            }

            callbackQuery("ca") { bot, update ->
                update.callbackQuery?.let {
                    it.data
                    val chatId = it.message?.chat?.id ?: return@callbackQuery
                    bot.sendMessage(
                        chatId = chatId,
                        text = "выберите",
                        replyMarkup = InlineKeyboardMarkup(
                            listOf(
                                listOf(InlineKeyboardButton("билет1", callbackData = "quality")),
                                listOf(InlineKeyboardButton("билет2", callbackData = "quality"))
                            )
                        )
                    )
                }
            }

            callbackQuery("quality") { bot, update ->
                update.callbackQuery?.let {
                    val chatId = it.message?.chat?.id ?: return@callbackQuery
                    bot.sendPoll(
                        chatId = chatId,
                        type = QUIZ,
                        question = "Ура! Вы приобрели билет. Довольны качеством сервиса?",
                        options = listOf("Да, доволен", "Средне...", "Нет, не доволен"),
                        correctOptionId = 0,
                        isAnonymous = false
                    )
                }
            }

            channel { bot, update ->// unused?
                println("handle channel: update.message?.text: ${update.message?.text}")
            }

            text(/* may set text */) { bot, update ->
                println("handle text: update.message?.text: ${update.message?.text}, chatId: ${update.message?.chat?.id}")
            }

            telegramError { _, telegramError ->
                println(telegramError.getErrorMessage())
            }
        }
    }

    bot.startPolling()
    bot.sendMessage(
        chatId = CHAT_ID,
        text = "Navigation buttons!",
        replyMarkup = KeyboardReplyMarkup(
            keyboard = listOf(
                listOf(
                    KeyboardButton(
                        "/start"
                    ),
                    KeyboardButton("button2")
                )
            ), resizeKeyboard = true
        )
    )
}
