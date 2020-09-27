package com.sample

import com.jakewharton.crossword.TextCanvas
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

const val HEIGHT = 25
const val WIDTH = 80

@Suppress("unused")
fun runConsoleApp() {
    val panel: ConsolePanel = consolePanelView {
        title("ЗАГОЛОВОК")
        label("hi, how are you?")
        checkBox("выбери меня", true) {

        }
        button("push me") {

        }
        bottomRow {
            button("Поиск") {

            }
            button("Мои заказы") {

            }
            button("Настройки") {

            }
        }
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

            panel.rows.forEachIndexed { row: Int, rowData: ConsoleRow ->
                rowData.views.forEachIndexed { col: Int, view: ConsoleView ->
                    canvas.write(10 + row * 2, 1 + col * 20, view.render())
                }
            }
            panel.bottomRows.forEachIndexed { row: Int, rowData: ConsoleRow ->
                rowData.views.forEachIndexed { col: Int, view: ConsoleView ->
                    canvas.write(HEIGHT - 2 - row * 2, 1 + col * 20, view.render())
                }
            }
            println(canvas)
//            System.in.readLine
            userInput = readLine()
            delay(300)
        }
    }
}

//Нажатая кнопка 47
//44 подсветка цифры для кнопки
//Конпка 32
fun String.color(clr: Int) = "\u001B[${clr}m${this}\u001B[0m"
fun ConsoleView.render(): String =
    when (this) {
        is ConsoleView.Button -> {
            label.color(32) + " (3)".color(44)
        }
        is ConsoleView.Label -> {
            str
        }
        is ConsoleView.Title -> {
            str.color(36)
        }
        is ConsoleView.CheckBox -> {
            "[v]$label".color(32)
        }
        is ConsoleView.TextInput -> {
            "text input"
        }
        is ConsoleView.PasswordInput -> {
            "password"
        }
    }
