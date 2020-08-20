package com.sample

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Spacer
import androidx.ui.layout.preferredHeight
import androidx.ui.material.Button
import androidx.ui.unit.dp
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
