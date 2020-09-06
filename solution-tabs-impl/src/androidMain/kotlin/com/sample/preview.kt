//package com.sample
//
//import androidx.compose.foundation.Box
//import androidx.compose.foundation.ContentGravity
//import androidx.compose.foundation.Text
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.ui.tooling.preview.Preview
//
//@Preview()
//@Composable
//fun tabsPreview() {
//    val solutionAndroid = SolutionTabsAndroidImpl(
//        SolutionTabsImpl(), object : SolutionTabSearchAndroidApi {
//            @Composable
//            override fun renderMainScreen() {
//                Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center,
//                    backgroundColor = Color(0xFF_FF_CC_CC)
//                ) {
//                    Text("stub", color = Color(0xFF888888))
//                }
//            }
//        },
//        object : SolutionOrderAndroidApi {
//            override fun renderNearestOrder() {
//
//            }
//
//            override fun renderAllOrders() {
//
//            }
//        },
//        object : SolutionSettingsApiAndroid {
//            @Composable
//            override fun renderSettings() {
//
//            }
//        }
//    )
//    solutionAndroid.renderScaffold()
//}
