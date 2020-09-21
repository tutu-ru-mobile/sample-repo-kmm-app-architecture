package mvi

import BrowserStorage
import Screen
import State
import WordState
import com.sample.createStore
import findNextWord

val store = createStore(State()) { state, intent:Intent ->
    when (intent) {
        is Intent.ChooseDictionary -> {
            if (state.screen is Screen.Dictionaries) {
                val contains = state.screen.selected.contains(intent.dictionary)
                state.copy(
                    screen = state.screen.copy(
                        selected = if (contains) {
                            state.screen.selected - intent.dictionary
                        } else {
                            state.screen.selected + intent.dictionary
                        }
                    )
                )
            } else {
                state
            }
        }
        is Intent.StartWordScreen -> {
            if (state.screen is Screen.Dictionaries) {
                val words = state.screen.selected.flatMap { it.words }.distinct()
                state.copy(
                    screen = Screen.Words(
                        words = words,
                        word = findNextWord(null, words, BrowserStorage),
                        wordState = WordState.Hidden
                    )
                )
            } else {
                state
            }
        }
        is Intent.MarkWord -> {
            if (state.screen is Screen.Words) {
                state.copy(
                    screen = when (state.screen.wordState) {
                        is WordState.Hidden -> {
                            if (intent.success) {
                                state.screen.copy(
                                    word = findNextWord(
                                        state.screen.word,
                                        state.screen.words,
                                        BrowserStorage
                                    ),
                                    wordState = WordState.Hidden
                                )
                            } else {
                                state.screen.copy(
                                    wordState = WordState.Fail
                                )
                            }
                        }
                        is WordState.Open -> {
                            state.screen.copy(
                                word = findNextWord(
                                    state.screen.word,
                                    state.screen.words,
                                    BrowserStorage
                                ),
                                wordState = WordState.Hidden
                            )
                        }
                        is WordState.Fail -> {
                            throw Error("bad variant: is WordState.Fail")
                        }
                    }
                )
            } else {
                state
            }
        }
        is Intent.OpenWord -> {
            if (state.screen is Screen.Words) {
                state.copy(
                    screen = state.screen.copy(
                        wordState = WordState.Open
                    )
                )
            } else {
                state
            }
        }
        is Intent.NextWord -> {
            if (state.screen is Screen.Words) {
                state.copy(
                    screen = state.screen.copy(
                        word = findNextWord(state.screen.word, state.screen.words, BrowserStorage),
                        wordState = WordState.Hidden
                    )
                )
            } else {
                state
            }
        }
    }
}
