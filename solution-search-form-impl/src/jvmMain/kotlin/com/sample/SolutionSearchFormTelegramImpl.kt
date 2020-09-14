package com.sample

class SolutionSearchFormTelegramImpl(
    val commonImpl: SolutionSearchFormImpl
) : SolutionSearchFormTelegramApi {

    override fun renderSearchForm(): TelegramView {
        return TelegramView.Container(
            listOf(
                TelegramView.Message(
                    "Выберите пункт отправления, прибытия и начните поиск билетов.\n",
                    listOf(
                        listOf(
                            TelegramButton("Откуда: "  +commonImpl.store.state.searchFrom) {

                            }
                        ),
                        listOf(
                            TelegramButton("Куда: "  +commonImpl.store.state.searchTo) {

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

