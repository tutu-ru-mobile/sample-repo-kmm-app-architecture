package com.sample

import com.jakewharton.crossword.TextCanvas
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.concurrent.thread

const val HEIGHT = 25
const val WIDTH = 80

sealed class Intent {
    object UpdateCounter : Intent()
    class ButtonClick(val buttonStr: String) : Intent()
    class From(val txt: String) : Intent()
}

@Suppress("unused")
fun runConsoleApp() {
    val store = createStore(State(0)) { state, intent: Intent ->
        when (intent) {
            is Intent.UpdateCounter -> {
                state.copy(
                    counter = state.counter + 1
                )
            }
            is Intent.ButtonClick -> {
                state.copy(
                    input = intent.buttonStr
                )
            }
            is Intent.From -> {
                state.copy(
                    from = intent.txt
                )
            }
        }
    }

    fun renderState(state: State) = consolePanelView {
        title("ЗАГОЛОВОК")
        label("hi, how are you?")
        checkBox("выбери меня", true) {

        }
        button("button1") {
            store.send(Intent.ButtonClick("btn1"))
        }
        button("button2") {
            store.send(Intent.ButtonClick("btn2"))
        }
        textInput("Откуда", state.from) {
            store.send(Intent.From(it))
        }
        label(state.toString())
        bottomRow {
            button("Поиск") {

            }
            button("Мои заказы", true) {

            }
            button("Настройки") {

            }
        }
    }

    val stateFlow = store.stateFlow
    GlobalScope.launch {
        while (true) {
            delay(3000)
            store.send(Intent.UpdateCounter)
        }
    }
    //thanks to: https://github.com/JakeWharton/crossword
    val mutableCallbackList: MutableList<(String) -> Unit> = CopyOnWriteArrayList()
    GlobalScope.launch {
        stateFlow.collectLatest { state ->
            val panel = renderState(state)
            System.out.print("\u001b[H\u001b[2J")
            System.out.flush()
            val canvas = TextCanvas(WIDTH, HEIGHT)

            var nextNumberCallback = 1
            mutableCallbackList.clear()
            val registerCallbacks: RegisterCallback = object : RegisterCallback {
                override fun registerNumberCallback(callback: () -> Unit): String {
                    val result = registerStringCallback(nextNumberCallback.toString(), callback)
                    nextNumberCallback++
                    return result
                }

                override fun registerStringCallback(str: String, callback: () -> Unit): String {
                    registerAllCallback {
                        if (it == str) {
                            callback()
                        }
                    }
                    return str
                }

                override fun registerAllCallback(callback: (String) -> Unit) {
                    mutableCallbackList.add(callback)
                }
            }
            panel.rows.forEachIndexed { row: Int, rowData: ConsoleRow ->
                rowData.views.forEachIndexed { col: Int, view: ConsoleView ->
                    canvas.write(1 + row * 2, 1 + col * 20, view.render(registerCallbacks))
                }
            }
            panel.bottomRows.forEachIndexed { row: Int, rowData: ConsoleRow ->
                rowData.views.forEachIndexed { col: Int, view: ConsoleView ->
                    canvas.write(HEIGHT - 2 - row * 2, 1 + col * 20, view.render(registerCallbacks))
                }
            }

            println(canvas)
        }
    }
    thread {
        while (true) {
            val userInput = readLine()
            if (userInput != null) {
                mutableCallbackList.forEach { callback ->
                    callback(userInput)
                }
            }
        }
    }
}

interface RegisterCallback {
    fun registerNumberCallback(callback: () -> Unit): String
    fun registerStringCallback(str: String, callback: () -> Unit): String
    fun registerAllCallback(callback: (String) -> Unit)
}

//Нажатая кнопка 47
//44 подсветка цифры для кнопки
//Конпка 32
fun String.color(clr: Int) = "\u001B[${clr}m${this}\u001B[0m"
fun ConsoleView.render(callbacks: RegisterCallback): String =
    when (this) {
        is ConsoleView.Button -> {
            if (selected) {
                label.color(47)
            } else {
                val cmdStr = callbacks.registerNumberCallback {
                    onClick()
                }
                "($cmdStr)".color(44) + label.color(32)
            }
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
            val cmdStr = callbacks.registerNumberCallback {
                println("Напишите $label?")
                callbacks.registerAllCallback {
                    onEdit(it)
                }
            }
            "($cmdStr)".color(44) + label.color(32) + ": " + value
        }
        is ConsoleView.PasswordInput -> {
            "password"
        }
    }

data class State(
    val counter: Int,
    val input: String = "",
    val from: String = "Мск"
)
