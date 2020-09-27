package com.sample

import com.jakewharton.crossword.TextCanvas
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

const val HEIGHT = 20
const val WIDTH = 80

fun runConsoleApp() {
    val panel: ConsolePanel = consolePanelView {
        label("hi")
    }
    //thanks to: https://github.com/JakeWharton/crossword
    var userInput: String? = null
    runBlocking {
        while (true) {
            System.out.print("\u001b[H\u001b[2J");
            System.out.flush()
            val canvas = TextCanvas(WIDTH, HEIGHT)
            canvas.write(1, 5, "Hello $userInput")
            canvas.write(6, 10, Random.nextInt().toString())
            canvas.write(4, 7, "Much monospace")
            canvas.write(0, 0, "\u001B[31mXyz\u001B[0m")
            canvas.write(0, 2, "\u001B[34mO\u001B[0m")

            panel.rows.forEachIndexed { row: Int, rowData: ConsoleRow ->
                rowData.views.forEachIndexed { col: Int, view: ConsoleView ->
                    canvas.write(10 + row * 2, col * 20, view.render())
                }
            }
            println(canvas)
//            System.in.readLine
            userInput = readLine()
            delay(300)
        }
    }
}

fun ConsoleView.render(): String =
    when (this) {
        is ConsoleView.Button -> {
            "button"
        }
        is ConsoleView.Label -> {
            str
        }
        is ConsoleView.Title -> {
            "title"
        }
        is ConsoleView.CheckBox -> {
            "checkbox"
        }
        is ConsoleView.TextInput -> {
            "text input"
        }
        is ConsoleView.PasswordInput -> {
            "password"
        }
    }