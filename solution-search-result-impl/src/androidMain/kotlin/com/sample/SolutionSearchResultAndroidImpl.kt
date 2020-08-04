package com.sample

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.gesture.scrollorientationlocking.Orientation
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBorder
import androidx.ui.foundation.gestures.rememberScrollableController
import androidx.ui.foundation.gestures.scrollable
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.unit.dp
import androidx.ui.unit.sp

class SolutionSearchResultAndroidImpl(
    val common: SolutionSearchResultImpl,
    val searchStart: SolutionSearchStartApi
) : SolutionSearchResultAndroidApi {

    @Composable
    fun renderTicket(ticket: Ticket) {
        Row(
            modifier = Modifier.padding(8.dp) +
                    Modifier.fillMaxWidth() +
                    Modifier.drawBorder(2.dp, color = Color(0xFF_44_44_44)) +
                    Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("${ticket.txt} - ${ticket.price} р.", fontSize = 20.sp)
            Button(onClick = {
                common.store.send(SolutionSearchResultImpl.Action.BuyTicket(ticket))
            }) {
                Text("Купить")
            }
        }
    }

    @Composable
    override fun renderSearchResult() {
        val offset = state { 0f }
        Column(modifier = Modifier.scrollable(
            orientation = Orientation.Vertical,
            controller = rememberScrollableController { delta ->
                offset.value = offset.value + delta
                delta
            }
        )) {

            Text("${searchStart.getSearchQuery()}")
            Text("Результат поиска:")
            common.store.state.tickets.forEach {
                renderTicket(it)
            }
            if(common.store.state.notEnoughMoney) {
                Text("Не достаточно денег")
            }
        }
    }

}
