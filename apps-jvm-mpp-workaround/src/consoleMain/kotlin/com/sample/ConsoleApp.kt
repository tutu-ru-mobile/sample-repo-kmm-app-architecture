package com.sample

import com.jakewharton.crossword.TextCanvas
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.concurrent.thread

const val HEIGHT = 25
const val CELL_WIDTH = 25
const val WIDTH = CELL_WIDTH*4

@Suppress("unused")
fun runConsoleApp() {
    val di = AppDiConsole()
    val stateFlow = di.common.globalStateFlow

    //thanks to: https://github.com/JakeWharton/crossword
    val mutableCallbackList: MutableList<(String) -> Unit> = CopyOnWriteArrayList()
    GlobalScope.launch {
        stateFlow.collectLatest { state ->
//            val panel = renderState(state)
            val panel = di.solutionTabsConsole.renderScaffold()
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
                    canvas.write(1 + row * 2, 1 + col * CELL_WIDTH, view.render(registerCallbacks))
                }
            }
            panel.bottomRows.forEachIndexed { row: Int, rowData: ConsoleRow ->
                rowData.views.forEachIndexed { col: Int, view: ConsoleView ->
                    canvas.write(HEIGHT - 2 - row * 2, 1 + col * CELL_WIDTH, view.render(registerCallbacks))
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

