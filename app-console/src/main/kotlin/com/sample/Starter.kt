package com.sample

import com.jakewharton.crossword.*

fun main() {
    //https://github.com/JakeWharton/crossword
    val canvas = TextCanvas(40, 8)
    canvas.write(1, 20, "Such canvas")
    canvas.write(6, 30, "Very 2D")
    canvas.write(4, 7, "Much monospace")
    canvas.write(0, 0, "\u001B[31mX\u001B[0m")
    canvas.write(0, 2, "\u001B[34mO\u001B[0m")
    println(canvas)
    println(canvas)
}
