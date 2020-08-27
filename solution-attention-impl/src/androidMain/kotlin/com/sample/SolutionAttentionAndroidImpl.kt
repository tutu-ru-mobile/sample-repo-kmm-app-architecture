package com.sample

import androidx.compose.foundation.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import com.sample.compose.CheckBoxWithLabel
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
