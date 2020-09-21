package com.sample

import react.RBuilder
import react.dom.h2

class SolutionSettingsAndroidImpl(
    val commonImpl: SolutionSettingsImpl
//    val authAndroid: SolutionAuthAndroidApi,
//    val bonus: SolutionBonusAndroidApi,
//    val ab: SolutionAbAndroidApi
) : SolutionSettingsBrowserApi {

    override fun renderSettings(react: RBuilder) {
        react.apply {
            h2 {
                +"renderSettings"
            }
        }
//        Central {
//            authAndroid.renderLoginForm()
//            Spacer(Modifier.preferredHeight(8.dp))
//            bonus.renderBonusesAndRefillButton()
//        }
//        Spacer(Modifier.preferredHeight(50.dp))
//        if (commonImpl.isDeveloperSettingsAvailable()) {
//            ab.renderAbSettings()
//        } else {
//            Button(onClick = {
//                commonImpl.actionOpenDeveloperSettings()
//            }) {
//                Text("Debug settings")
//            }
//        }
    }

}
