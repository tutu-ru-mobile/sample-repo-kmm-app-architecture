package com.sample

import com.jakewharton.crossword.TextCanvas
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.random.Random

const val HEIGHT = 25
const val WIDTH = 80

@Suppress("unused")
fun runConsoleApp() {
    val stateFlow = MutableStateFlow(State(counter = Random.nextInt()))
    GlobalScope.launch {
        while (true) {
            delay(500)
            stateFlow.value = State(counter = Random.nextInt())
        }
    }
    //thanks to: https://github.com/JakeWharton/crossword
    GlobalScope.launch {
        stateFlow.collectLatest { state ->
            val panel = renderState(state)
            System.out.print("\u001b[H\u001b[2J");
            System.out.flush()
            val canvas = TextCanvas(WIDTH, HEIGHT)
            panel.rows.forEachIndexed { row: Int, rowData: ConsoleRow ->
                rowData.views.forEachIndexed { col: Int, view: ConsoleView ->
                    canvas.write(1 + row * 2, 1 + col * 20, view.render())
                }
            }
            panel.bottomRows.forEachIndexed { row: Int, rowData: ConsoleRow ->
                rowData.views.forEachIndexed { col: Int, view: ConsoleView ->
                    canvas.write(HEIGHT - 2 - row * 2, 1 + col * 20, view.render())
                }
            }
            println(canvas)
        }
    }
    thread {
        while(true) {
            val userInput = readLine()
            if (userInput != null) {
                stateFlow.value = State(counter = 0, input = userInput)
            }
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

data class State(
    val counter: Int,
    val input: String = ""
)

fun renderState(state: State) = consolePanelView {
    title("ЗАГОЛОВОК")
    label("hi, how are you?")
    checkBox("выбери меня", true) {

    }
    button("push me") {

    }
    label(state.toString())
    bottomRow {
        button("Поиск") {

        }
        button("Мои заказы") {

        }
        button("Настройки") {

        }
    }
}
