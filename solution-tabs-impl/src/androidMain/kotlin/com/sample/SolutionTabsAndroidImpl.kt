package com.sample

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.material.BottomNavigation
import androidx.ui.material.BottomNavigationItem
import androidx.ui.material.Scaffold
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.AccountBalanceWallet
import androidx.ui.material.icons.filled.Menu
import androidx.ui.material.icons.filled.Search
import androidx.ui.unit.dp
import com.sample.compose.DefaultPadding
import com.sample.compose.WrapColorBox

class SolutionTabsAndroidImpl(
    val commonImpl: SolutionTabsImpl,
    val searchTabAndroid: SolutionTabSearchAndroidApi,
    val ordersTabAndroid: SolutionOrderAndroidApi,
    val settingsTabAndroid: SolutionSettingsApiAndroid
) : SolutionTabsAndroidApi {

    @Composable
    override fun renderScaffold() {
        Scaffold(
            bottomBar = {
                renderBottomNavigation()
            }
        ) {
            DefaultPadding {
                when (commonImpl.store.state.screen) {
                    is SolutionTabsImpl.Screen.Main -> {
                        searchTabAndroid.renderMainScreen()
                    }
                    is SolutionTabsImpl.Screen.Orders -> {
                        Column(modifier = Modifier.padding(16.dp)) {
                            ordersTabAndroid.renderAllOrders()
                        }
                    }
                    is SolutionTabsImpl.Screen.Settings -> {
                        Column(modifier = Modifier.padding(16.dp)) {
                            settingsTabAndroid.renderSettings()
                        }
                    }
                }
            }
        }
    }

    @Composable
    override fun renderBottomNavigation() {
        WrapColorBox(color = commonImpl.getColor()) {
            BottomNavigation() {
                BottomNavigationItem(
                    icon = {
                        Icon(asset = Icons.Filled.Search)
                    },
                    selected = commonImpl.store.state.screen == SolutionTabsImpl.Screen.Main,
                    text = {
                        Text("Поиск")
                    },
                    alwaysShowLabels = true,
                    onSelected = {
                        commonImpl.store.send(SolutionTabsImpl.Action.SelectMain)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(asset = Icons.Filled.AccountBalanceWallet)
                    },
                    text = {
                        Text("Мои билеты")
                    },
                    alwaysShowLabels = true,
                    selected = commonImpl.store.state.screen == SolutionTabsImpl.Screen.Orders,
                    onSelected = {
                        commonImpl.store.send(SolutionTabsImpl.Action.SelectOrders)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(asset = Icons.Filled.Menu)
                    },
                    text = {
                        Text("Настройки")
                    },
                    alwaysShowLabels = true,
                    selected = commonImpl.store.state.screen == SolutionTabsImpl.Screen.Settings,
                    onSelected = {
                        commonImpl.store.send(SolutionTabsImpl.Action.SelectSettings)
                    }
                )
            }
        }
    }

}

fun tempColorPicket() {
    val c1 = Color(0xFFB5F071)
}
