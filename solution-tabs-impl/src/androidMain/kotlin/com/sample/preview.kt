package com.sample

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.sample.compose.Central
import com.sample.compose.CheckBoxWithLabel
import com.sample.compose.WrapColorBox
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
