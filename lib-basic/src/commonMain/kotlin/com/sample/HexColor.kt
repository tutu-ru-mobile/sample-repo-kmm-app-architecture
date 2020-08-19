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

    val SOLUTION_BONUS = RED
    val SOLUTION_AUTH = BLUE
}
