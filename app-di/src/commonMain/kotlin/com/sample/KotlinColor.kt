package com.sample

class KotlinColor(val hexColor: Int) {
    val alpha get() = (hexColor ushr 24) and 0xFF
    val red get() = (hexColor ushr 16) and 0xFF
    val green get() = (hexColor ushr 8) and 0xFF
    val blue get() = (hexColor ushr 0) and 0xFF

    val floatAlpha get() = alpha / 255.0f
    val floatRed get() = red / 255.0f
    val floatGreen get() = green / 255.0f
    val floatBlue get() = blue / 255.0f
}

object KotlinColorsSingleton {
    val RED = KotlinColor(0xFF_FF_00_00.toInt())
    val GREEN = KotlinColor(0xFF_00_FF_00.toInt())
}

class KotlinColors {
    val RED = KotlinColor(0xFF_FF_00_00.toInt())
    val GREEN = KotlinColor(0xFF_00_FF_00.toInt())
}
