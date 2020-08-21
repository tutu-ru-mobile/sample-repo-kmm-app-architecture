package com.sample

class HexColor(val hexColor: Int) {
    val alpha get() = (hexColor ushr 24) and 0xFF
    val red get() = (hexColor ushr 16) and 0xFF
    val green get() = (hexColor ushr 8) and 0xFF
    val blue get() = (hexColor ushr 0) and 0xFF

    val floatAlpha get() = alpha / 255.0f
    val floatRed get() = red / 255.0f
    val floatGreen get() = green / 255.0f
    val floatBlue get() = blue / 255.0f
}

object MyColors {
    val RED = HexColor(0xFFF12C2C.toInt())
    val GREEN = HexColor(0xFF4CAF50.toInt())
    val LIGHT_GREEN = HexColor(0xFFB5F071.toInt())
    val BLUE = HexColor(0xFF3852E2.toInt())
    val LIGHT_BLUE = HexColor(0xFF3ED1E4.toInt())
    val YELLOW = HexColor(0xFF_FF_FF_00.toInt())
    val DARK_YELLOW = HexColor(0xFFFCE306.toInt())
    val VIOLET = HexColor(0xFF9C27B0.toInt())
    val RANDOM1 = HexColor(0xFF_18_82_48.toInt())
    val RANDOM2 = HexColor(0xFF_94_45_73.toInt())
    val RANDOM3 = HexColor(0xFF_44_99_77.toInt())
    val RANDOM4 = HexColor(0xFF_65_11_33.toInt())
    val RANDOM5 = HexColor(0xFF_65_11_33.toInt())
    val DARK_ORANGE = HexColor(0xFFFF9800.toInt())

    val SOLUTION_BONUS = RED
    val SOLUTION_AUTH = LIGHT_BLUE//+
    val SOLUTION_AB = LIGHT_GREEN
    val SOLUTION_SEARCH_FORM = BLUE
    val SOLUTION_ATTENTION = GREEN //+
    val SOLUTION_TABS = DARK_ORANGE
    val SOLUTION_WEATHER = DARK_YELLOW
    val SOLUTION_ORDER = VIOLET //+
    val SOLUTION_BUY = LIGHT_BLUE

}
