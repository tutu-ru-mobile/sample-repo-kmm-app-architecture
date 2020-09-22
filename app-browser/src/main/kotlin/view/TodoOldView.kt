package view

import OldState
import StoreItem
import allDictionaries
import com.sample.checkBox
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import mvi.Intent
import mvi.store
import react.RBuilder
import react.dom.br
import react.dom.button
import react.dom.div
import styled.css
import styled.styledDiv
import kotlin.js.Date

fun RBuilder.todoOldView() {//todo delete
    div {
        val state = OldState()
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
                            store.send(Intent.ChooseDictionary(dictionary))
                        }
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
                            +"..."
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
                            +"..."
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

    }
}
