package com.sample

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sample.compose.Central

class SolutionSettingsAndroidImpl(
    val commonImpl: SolutionSettingsImpl,
    val authAndroid: SolutionAuthAndroidApi,
    val bonus: SolutionBonusApiAndroid,
    val ab: SolutionAbAndroidApi
) : SolutionSettingsApiAndroid {

    @Composable
    override fun renderSettings() {
        Central {
            authAndroid.renderLoginForm()
            Spacer(Modifier.preferredHeight(8.dp))
            bonus.renderBonusesAndRefillButton()
        }
        Spacer(Modifier.preferredHeight(50.dp))
        if (commonImpl.isDeveloperSettingsAvailable()) {
            ab.renderAbSettings()
        } else {
            Button(onClick = {
                commonImpl.actionOpenDeveloperSettings()
            }) {
                Text("Debug settings")
            }
        }
    }

}
