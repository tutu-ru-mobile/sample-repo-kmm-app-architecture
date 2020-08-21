package com.sample

data class Ticket(
    val price: Int,
    val txt: String
) {
    /**
     * Уникальный id для SwiftUI
     */
    val id: String get() = hashCode().toString()
    fun getRefundMoneyAmount() = (price * 0.9).toInt() //TODO // 10 % комиссия за возврат билета
}
