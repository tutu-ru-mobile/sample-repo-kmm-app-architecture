package com.sample

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.unit.dp
import com.sample.compose.WrapColorBox

class SolutionAttentionAndroidImpl(
    val commonImpl: SolutionAttentionImpl,
    val solutionAuth: SolutionAuthApi,
    val weatherAndroid: SolutionWeatherAndroidApi,
    val orderAndroid: SolutionOrderAndroidApi,
    val solutionBonusAndroid: SolutionBonusApiAndroid,
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
