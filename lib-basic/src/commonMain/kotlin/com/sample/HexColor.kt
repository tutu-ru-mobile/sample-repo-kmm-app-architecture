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
    val RED = HexColor(0xFF_FF_00_00.toInt())
    val GREEN = HexColor(0xFF_00_FF_00.toInt())
    val BLUE = HexColor(0xFF_00_00_FF.toInt())
    val YELLOW = HexColor(0xFF_FF_FF_00.toInt())
    val VIOLET = HexColor(0xFF_EE_82_EE.toInt())
    val RANDOM1 = HexColor(0xFF_18_82_48.toInt())
    val RANDOM2 = HexColor(0xFF_94_45_73.toInt())

    val SOLUTION_BONUS = YELLOW
    val SOLUTION_AUTH = VIOLET
    val SOLUTION_AB = GREEN
    val SOLUTION_SEARCH_FORM = RANDOM1
    val SOLUTION_ATTENTION = RANDOM2
}
