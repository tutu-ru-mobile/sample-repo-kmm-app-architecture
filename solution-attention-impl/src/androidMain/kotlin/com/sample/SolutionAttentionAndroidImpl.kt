package com.sample

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.unit.dp

class SolutionAttentionAndroidImpl(
    val common: SolutionAttentionImpl,
    val auth: SolutionAuthApi,
    val weatherAndroid: SolutionWeatherAndroidApi,
    val orderAndroid: SolutionOrderAndroidApi,
    val walletAndroid: SolutionWalletApiAndroid,
    val ab: SolutionAbApi
) : SolutionAttentionAndroidApi {

    @Composable
    override fun renderMainScreenAttention() {
        Box(modifier = Modifier.padding(16.dp)) {
            Column {
                if (ab.isWalletAvailable()) {
                    walletAndroid.renderWallet()
                } else {
                    if (auth.isAuthorized()) {
                        orderAndroid.renderNearestOrder()
                    } else {
                        weatherAndroid.renderWeather()
                    }
                }
            }
        }
    }

}
