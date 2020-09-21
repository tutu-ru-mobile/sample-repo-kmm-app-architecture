package mvi

import BrowserStorage
import Screen
import State
import StoreItem
import WordState
import currentUnixTime
import findNextWord
import lib.Mvi

val store = Mvi.store<State, Intent, SideEffect>(
    State(),
    sideEffectHandler = { store, effect ->
        when (effect) {
            is SideEffect.LoadDeployTime -> {

            }
            is SideEffect.StoreWord -> {
                val item = BrowserStorage.getItem(effect.key) ?: StoreItem()
                val result = if (effect.success) {
                    item.copy(
                        successCount = item.successCount + 1,
                        changedUnixTime = currentUnixTime()
                    )
                } else {
                    item.copy(
                        failCount = item.failCount + 1,
                        changedUnixTime = currentUnixTime()
                    )
                }
                BrowserStorage.saveItem(effect.key, result)
            }
        }.let {}
    }
) { state, intent ->
    when (intent) {
        is Intent.LoadDeployTime -> {
            SideEffect.LoadDeployTime.onlySideEffect()
        }
        is Intent.SetDeployTime -> {
            state.copy(
                deployTime = intent.deployTime
            ).onlyState()
        }
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
                ).onlyState()
            } else {
                doNothing
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
                ).onlyState()
            } else {
                doNothing
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
                ).andEffect(
                    SideEffect.StoreWord(state.screen.word.hint, intent.success)
                )
            } else {
                doNothing
            }
        }
        is Intent.OpenWord -> {
            if (state.screen is Screen.Words) {
                state.copy(
                    screen = state.screen.copy(
                        wordState = WordState.Open
                    )
                ).onlyState()
            } else {
                doNothing
            }
        }
        is Intent.NextWord -> {
            if (state.screen is Screen.Words) {
                state.copy(
                    screen = state.screen.copy(
                        word = findNextWord(state.screen.word, state.screen.words, BrowserStorage),
                        wordState = WordState.Hidden
                    )
                ).onlyState()
            } else {
                doNothing
            }
        }
    }
}
