package com.sample

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
                    label = {
                        Text("Поиск")
                    },
                    alwaysShowLabels = true,
                    onSelect = {
                        commonImpl.store.send(SolutionTabsImpl.Action.SelectMain)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(asset = Icons.Filled.AccountBalanceWallet)
                    },
                    label = {
                        Text("Мои билеты")
                    },
                    alwaysShowLabels = true,
                    selected = commonImpl.store.state.screen == SolutionTabsImpl.Screen.Orders,
                    onSelect = {
                        commonImpl.store.send(SolutionTabsImpl.Action.SelectOrders)
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(asset = Icons.Filled.Menu)
                    },
                    label = {
                        Text("Настройки")
                    },
                    alwaysShowLabels = true,
                    selected = commonImpl.store.state.screen == SolutionTabsImpl.Screen.Settings,
                    onSelect = {
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
