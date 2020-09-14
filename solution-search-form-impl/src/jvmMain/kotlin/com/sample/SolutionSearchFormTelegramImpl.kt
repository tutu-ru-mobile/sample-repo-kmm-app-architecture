package com.sample

class SolutionSearchFormTelegramImpl(
    val commonImpl: SolutionSearchFormImpl,
    val inputStr: (text:String, cb: (String) -> Unit) -> Unit
) : SolutionSearchFormTelegramApi {

    override fun renderSearchForm(): TelegramView {
        return TelegramView.Container(
            listOf(
                TelegramView.Message(
                    "Выберите пункт отправления, прибытия и начните поиск билетов.\n",
                    listOf(
                        listOf(
                            TelegramButton("Откуда: " + commonImpl.store.state.searchFrom) {
                                inputStr("Откуда вы хотите поехать?") {
                                    println("action from $it")
                                    commonImpl.store.send(SolutionSearchFormImpl.Action.From(it))
                                }
                            }
                        ),
                        listOf(
                            TelegramButton("Куда: " + commonImpl.store.state.searchTo) {
                                inputStr("Куда вы хотите поехать?") {
                                    commonImpl.store.send(SolutionSearchFormImpl.Action.To(it))
                                }
                            }
                        ),
                        listOf(
                            TelegramButton("Начать поиск") {
                                commonImpl.store.send(SolutionSearchFormImpl.Action.Search)
                            }
                        )
                    )
                )
            )
        )
    }

}

