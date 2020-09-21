//package mvi

import react.RState

data class State(
    val deployTime: String = "",
    val screen: Screen = Screen.Dictionaries(allDictionaries.filter { it.useByDefault }.toSet())
) : RState

sealed class Screen {
    data class Dictionaries(
        val selected: Set<Dictionary>
    ) : Screen()

    data class Words(
        val words: List<Word>,
        val word: Word,
        val wordState: WordState
    ) : Screen()
}

data class Dictionary(
    val name: String,
    val useByDefault: Boolean,
    val words: List<Word>
)

data class Word(
    val hint: String,
    val secret: String
)

sealed class WordState {
    object Hidden : WordState()
    object Open : WordState()
    object Fail : WordState()
}