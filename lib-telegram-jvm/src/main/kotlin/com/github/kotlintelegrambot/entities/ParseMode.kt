package com.github.kotlintelegrambot.entities

import com.google.gson.annotations.SerializedName

enum class ParseMode(val modeName: String) {
    @SerializedName("Markdown") MARKDOWN("Markdown"),
    @SerializedName("HTML") HTML("HTML"),
    @SerializedName("MarkdownV2") MARKDOWN_V2("MarkdownV2")
}
