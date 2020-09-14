package com.sample

class TelegramScaffold(
    val content: TelegramView,
    val navButtons: List<TelegramButton>
)

sealed class TelegramView {
    class Container(
        val children: List<TelegramView>
    ) : TelegramView()

    class Message(
        val text: String,
        val buttons: List<List<TelegramButton>>? = null
    ) : TelegramView()


    companion object {
        fun Button(btnText: String, onClick: () -> Unit) =
            Message(
                text = "-",//todo
                buttons = listOf(
                    listOf(
                        TelegramButton(btnText, onClick)
                    )
                )
            )
    }

}

class TelegramButton(
    val text: String,
    val onClick: () -> Unit
)

