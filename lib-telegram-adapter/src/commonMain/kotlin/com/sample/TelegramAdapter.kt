package com.sample

class TelegramScaffold(
    val content: Content,
    val navButtons: List<Content.Button>
)

sealed class Content {
    class Container(
        val children: List<Content>
    ) : Content()

    class Message(
        val text: String
    ) : Content()

    class Button(
        val text: String,
        val onClick: () -> Unit
    ) : Content()
}

