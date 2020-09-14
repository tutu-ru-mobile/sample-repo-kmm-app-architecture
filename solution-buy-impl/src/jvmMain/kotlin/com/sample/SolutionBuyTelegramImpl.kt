package com.sample

class SolutionBuyTelegramImpl(
    val commonImpl: SolutionBuyImpl
//    val solutionBonusTelegram: SolutionBonusTelegramApi,
//    val solutionBonus: SolutionBonusApi
) : SolutionBuyTelegramApi {

    override fun renderBuy():Content {
        val ticket = commonImpl.getState().ticket
        return Content.Message("renderBuy ${ticket.txt}, за ${ticket.price} р.")
//            Column {
//                Text("Билет ${commonImpl.store.state.ticket.txt}", fontSize = 20.sp)
//                Spacer(Modifier.preferredHeight(16.dp))
//                solutionBonusAndroid.renderBonusToggle()
//                Spacer(Modifier.preferredHeight(16.dp))
//                Button(onClick = {
//                    commonImpl.store.send(SolutionBuyImpl.Action.BuyTicket())
//                }) {
//                    Column {
//                        if (solutionBonus.canBuyWithBonus()) {
//                            Text(
//                                "${commonImpl.getState().ticket.price} р.",
//                                textDecoration = TextDecoration.LineThrough
//                            )
//                        }
//                        Text("Купить за ${commonImpl.getPrice()} р.")
//                    }
//                }
//                Spacer(Modifier.preferredHeight(16.dp))
//                Button(onClick = {
//                    commonImpl.store.send(SolutionBuyImpl.Action.Back())
//                }, backgroundColor = HexColor(0x55FF0000).toComposeColor()) {
//                    Text("Отмена")
//                }
//            }
    }

}
