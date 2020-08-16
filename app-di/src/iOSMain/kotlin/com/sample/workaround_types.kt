package com.sample

// Чтобы типы из зависимых gradle модулей были видны в XCode нужно их тут явно прописать. Это временный костыль.
class TypeSolutionTabs {
    var state: SolutionTabsImpl.State? = null
    var screen: SolutionTabsImpl.Screen? = null
    var screenMain: SolutionTabsImpl.Screen.Main? = null
    var screenOrders: SolutionTabsImpl.Screen.Orders? = null
    var screenSettings: SolutionTabsImpl.Screen.Settings? = null
    var actionSelectMain: SolutionTabsImpl.Action.SelectMain? = null
    var actionSelectOrders: SolutionTabsImpl.Action.SelectOrders? = null
    var actionSelectSettings: SolutionTabsImpl.Action.SelectSettings? = null
}

