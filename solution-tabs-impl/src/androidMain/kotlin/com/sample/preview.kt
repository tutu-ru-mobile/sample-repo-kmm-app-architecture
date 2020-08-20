package com.sample

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxSize
import androidx.ui.tooling.preview.Preview

@Preview()
@Composable
fun tabsPreview() {
    val solutionAndroid = SolutionTabsAndroidImpl(
        SolutionTabsImpl(), object : SolutionTabSearchAndroidApi {
            @Composable
            override fun renderMainScreen() {
                Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center,
                    backgroundColor = Color(0xFF_FF_CC_CC)
                ) {
                    Text("stub", color = Color(0xFF888888))
                }
            }
        },
        object : SolutionOrderAndroidApi {
            override fun renderNearestOrder() {

            }

            override fun renderAllOrders() {

            }
        },
        object : SolutionSettingsApiAndroid {
            @Composable
            override fun renderSettings() {

            }
        }
    )
    solutionAndroid.renderScaffold()
}
