package com.sample

import com.jakewharton.crossword.TextCanvas
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    //thanks to: https://github.com/JakeWharton/crossword
    var userInput:String?=null
    runBlocking {
        while (true) {
            System.out.print("\u001b[H\u001b[2J");
            System.out.flush()
            val canvas = TextCanvas(40, 8)
            canvas.write(1, 5, "${todoDepJvm()} $userInput")
            canvas.write(6, 10, Random.nextInt().toString())
            canvas.write(4, 7, "Much monospace")
            canvas.write(0, 0, "\u001B[31mX\u001B[0m")
            canvas.write(0, 2, "\u001B[34mO\u001B[0m")
            println(canvas)
//            System.in.readLine
            userInput = readLine()
            delay(300)
        }
    }

    todoDepJvm()
    todoDepCommon()
}
