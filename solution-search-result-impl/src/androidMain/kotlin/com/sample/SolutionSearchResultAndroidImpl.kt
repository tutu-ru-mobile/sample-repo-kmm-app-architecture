package com.sample

import androidx.compose.foundation.Text
import androidx.compose.foundation.drawBorder
import androidx.compose.foundation.gestures.rememberScrollableController
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.state
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            Spacer(Modifier.preferredHeight(16.dp))
            Button(onClick = {
                common.store.send(SolutionSearchResultImpl.Action.Back())
            }) {
                Text("Назад")
            }
        }
    }

}
