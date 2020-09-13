package com.sample

class SolutionSearchResultTelegramImpl(
    val common: SolutionSearchResultImpl
//    val searchStart: SolutionSearchStartApi
) : SolutionSearchResultTelegramApi {

//    fun renderTicket(ticket: Ticket):Content {
////        Row(
////            modifier = Modifier.padding(8.dp) +
////                    Modifier.fillMaxWidth() +
////                    Modifier.drawBorder(2.dp, color = Color(0xFF_44_44_44)) +
////                    Modifier.padding(8.dp),
////            horizontalArrangement = Arrangement.SpaceBetween
////        ) {
////            Text("${ticket.txt} - ${ticket.price} р.", fontSize = 20.sp)
////            Button(onClick = {
////                common.store.send(SolutionSearchResultImpl.Action.BuyTicket(ticket))
////            }) {
////                Text("Купить")
////            }
////        }
//    }

    override fun renderSearchResult():Content {
        return Content.Message("renderSearchResult")
//        val offset = state { 0f }
//        Column(modifier = Modifier.scrollable(
//            orientation = Orientation.Vertical,
//            controller = rememberScrollableController { delta ->
//                offset.value = offset.value + delta
//                delta
//            }
//        )) {
//
//            Text("${searchStart.getSearchQuery()}")
//            Text("Результат поиска:")
//            common.store.state.tickets.forEach {
//                renderTicket(it)
//            }
//            if(common.store.state.notEnoughMoney) {
//                Text("Не достаточно денег")
//            }
//            Spacer(Modifier.preferredHeight(16.dp))
//            Button(onClick = {
//                common.store.send(SolutionSearchResultImpl.Action.Back())
//            }) {
//                Text("Назад")
//            }
//        }
    }

}
