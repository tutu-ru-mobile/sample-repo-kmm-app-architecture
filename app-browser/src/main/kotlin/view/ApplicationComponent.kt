package view

import State
import StoreItem
import allDictionaries
import kotlinx.css.*
import lib.MviComponent
import kotlinx.html.js.onClickFunction
import mvi.Intent
import mvi.store
import react.dom.br
import react.dom.button
import react.dom.div
import styled.css
import styled.styledDiv
import kotlin.js.Date

val YES_NO_PT = 50.pt
val WORD_PT = 22.pt

class ApplicationComponent : MviComponent<State, Intent>(
    store,
    { state ->
        styledDiv {
            css {
                padding(32.px, 16.px)
            }

            div {
                styledDiv {
                    css {
                        marginBottom = 32.px
                        textAlign = TextAlign.center
                        fontSize = 20.pt
                    }

                    when (state.screen) {
                        is Screen.Dictionaries -> {
                            button {
                                attrs {
                                    onClickFunction = {
                                        BrowserStorage.clear()
                                    }
                                }
                                styledDiv {
                                    css {
                                        fontSize = 30.pt
                                    }
                                    +"Очистить статистику"
                                }
                            }
                            styledDiv {
                                css {
                                    fontSize = 24.pt
                                }
                                br {}
                                +("Обновление словарей: " + state.deployTime)
                                br {}
                                br {}
                            }
                            allDictionaries.forEach { dictionary ->
                                styledDiv {
                                    val selected = state.screen.selected.contains(dictionary)
                                    checkBox(
                                        "Словарь ${dictionary.name} (${dictionary.words.size} слов)",
                                        selected
                                    ) {
                                        store.dispatch(Intent.ChooseDictionary(dictionary))
                                    }
                                }
                            }
                            button {
                                attrs {
                                    onClickFunction = {
                                        store.dispatch(Intent.StartWordScreen)
                                    }
                                }
                                styledDiv {
                                    css {
                                        fontSize = YES_NO_PT
                                    }
                                    +"Начать"
                                }
                            }
                        }
                        is Screen.Words -> {
                            when (state.screen.wordState) {
                                is WordState.Hidden -> {
                                    styledDiv {
                                        +"Знаешь слово ?"
                                        br {}
                                        br {}
                                        button {
                                            attrs {
                                                onClickFunction = {
                                                    store.dispatch(Intent.OpenWord)
                                                }
                                                styledDiv {
                                                    css {
                                                        fontSize = 30.pt
                                                    }
                                                    +"Показать перевод"
                                                }
                                            }
                                        }
                                        br {}
                                        br {}
                                        styledDiv {
                                            css {
                                                fontWeight = FontWeight.bold
                                                fontSize = WORD_PT
                                            }
                                            +state.screen.word.hint
                                        }
                                        br {}
                                        button {
                                            attrs {
                                                onClickFunction = {
                                                    store.dispatch(Intent.MarkWord(true))
                                                }
                                            }
                                            styledDiv {
                                                css {
                                                    fontSize = YES_NO_PT
                                                    color = Color.darkGreen
                                                }
                                                +"Да"
                                            }
                                        }
                                        +"..."
                                        button {
                                            attrs {
                                                onClickFunction = {
                                                    store.dispatch(Intent.MarkWord(false))
                                                }
                                            }
                                            styledDiv {
                                                css {
                                                    fontSize = YES_NO_PT
                                                    color = Color.darkRed
                                                }
                                                +"Нет"
                                            }
                                        }
                                    }
                                }
                                is WordState.Open -> {
                                    styledDiv {
                                        +"Знаешь слово ?"
                                        br {}
                                        br {}
                                        styledDiv {
                                            css {
//                                                fontWeight = FontWeight.bold
                                                fontSize = WORD_PT
                                            }
                                            +state.screen.word.hint
                                        }
                                        br {}
                                        br {}
                                        styledDiv {
                                            css {
                                                fontWeight = FontWeight.bold
                                                fontSize = WORD_PT
                                            }
                                            +state.screen.word.secret
                                        }
                                        br {}
                                        button {
                                            attrs {
                                                onClickFunction = {
                                                    store.dispatch(Intent.MarkWord(true))
                                                }
                                            }
                                            styledDiv {
                                                css {
                                                    fontSize = YES_NO_PT
                                                    color = Color.darkGreen
                                                }
                                                +"Да"
                                            }
                                        }
                                        +"..."
                                        button {
                                            attrs {
                                                onClickFunction = {
                                                    store.dispatch(Intent.MarkWord(false))
                                                }
                                            }
                                            styledDiv {
                                                css {
                                                    fontSize = YES_NO_PT
                                                    color = Color.darkRed
                                                }
                                                +"Нет"
                                            }
                                        }
                                    }
                                }
                                is WordState.Fail -> {
                                    styledDiv {
                                        +"Вот как правильно:"
                                        br {}
                                        br {}
                                        styledDiv {
                                            css {
//                                                fontWeight = FontWeight.bold
                                                fontSize = WORD_PT
                                            }
                                            +state.screen.word.hint
                                        }
                                        br {}
                                        br {}
                                        styledDiv {
                                            css {
                                                fontWeight = FontWeight.bold
                                                fontSize = WORD_PT
                                            }
                                            +state.screen.word.secret
                                        }

                                        br {}
                                        button {
                                            attrs {
                                                onClickFunction = {
                                                    store.dispatch(Intent.NextWord)
                                                }
                                            }
                                            styledDiv {
                                                css {
                                                    fontSize = YES_NO_PT
                                                }
                                                +"Дальше"
                                            }
                                        }
                                    }
                                }
                            }
                            br {}
                            br {}
                            styledDiv {
                                css {
                                    fontSize = 20.pt
                                    color = Color.gray
                                }
                                val stat =
                                    BrowserStorage.getItem(state.screen.word.hint) ?: StoreItem()
                                +"Статистика по этоу слову:"
                                br {}
                                br {}
                                +"успех: ${stat.successCount}, ошибка: ${stat.failCount},"
                                br {}
                                br {}
                                +"изменение: ${if (stat.changedUnixTime == 0) { "новое слово" } else {
                                        Date(stat.changedUnixTime * 1000L).toLocaleString()
                                    }}"
                            }
                        }
                    }

//                    gitHubRepoView(
//                        GitHubRepoProps(
//                            gitHubRepo = repo,
//                            onClickCommitLogBtn = {
//                                store.dispatch(
//                                    AppIntent.LoadCommits(
//                                        repo.organization,
//                                        repo.name
//                                    )
//                                )
//                            }
//                        )
//                    )
                }
            }
        }
    }
)
