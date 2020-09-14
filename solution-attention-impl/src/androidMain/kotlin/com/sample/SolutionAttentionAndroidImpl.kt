package com.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.sample.compose.WrapColorBox

class SolutionAttentionAndroidImpl(
    val commonImpl: SolutionAttentionImpl,
    val solutionAuth: SolutionAuthApi,
    val weatherAndroid: SolutionWeatherAndroidApi,
    val orderAndroid: SolutionOrderAndroidApi,
    val solutionBonusAndroid: SolutionBonusAndroidApi,
    val ab: SolutionAbApi
) : SolutionAttentionAndroidApi {

    @Composable
    override fun renderMainScreenAttention() {
        WrapColorBox(color = commonImpl.getColor()) {
            Column {
                solutionBonusAndroid.renderBonusCount()
                if (solutionAuth.isAuthorized()) {
                    orderAndroid.renderNearestOrder()
                } else {
                    weatherAndroid.renderWeather()
                }
            }
        }
    }

}
