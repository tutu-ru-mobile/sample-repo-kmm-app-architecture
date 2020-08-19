package com.sample

class KotlinColor(val hexColor: Int) {
    val alpha get() = (hexColor ushr 24) and 0xFF
    val red get() = (hexColor ushr 16) and 0xFF
    val green get() = (hexColor ushr 8) and 0xFF
    val blue get() = (hexColor ushr 0) and 0xFF

    val floatAlpha get() = alpha
    val floatRed get() = red
    val floatGreen get() = green
    val floatBlue get() = blue
}

object KotlinColorsSingleton {
    val RED = KotlinColor(0xFF_FF_00_00.toInt())
    val GREEN = KotlinColor(0xFF_00_FF_00.toInt())
}

class KotlinColors {
    val RED = KotlinColor(0xFF_FF_00_00.toInt())
    val GREEN = KotlinColor(0xFF_00_FF_00.toInt())
}
