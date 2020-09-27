package com.sample

private const val INTERACT_COLOR = 32
private const val FOCUS_COLOR = 47
private const val TITLE_COLOR = 36
private const val CMD_COLOR = 44

fun String.color(clr: Int) = "\u001B[${clr}m${this}\u001B[0m"
fun ConsoleView.render(callbacks: RegisterCallback): String =
    when (this) {
        is ConsoleView.Button -> {
            val labelColor = if (selected) FOCUS_COLOR else INTERACT_COLOR
            val cmdStr = cmd?.let {
                callbacks.registerStringCallback(it) {
                    onClick()
                }
            } ?: callbacks.registerNumberCallback {
                onClick()
            }
            "(${cmdStr.toUpperCase()})".color(CMD_COLOR) + " " + label.color(labelColor)
        }
        is ConsoleView.Label -> {
            str
        }
        is ConsoleView.Title -> {
            str.color(TITLE_COLOR)
        }
        is ConsoleView.CheckBox -> {
            val cmdStr = callbacks.registerNumberCallback {
                onClick()
            }
            val checkStr = if(value) "[*]" else "[ ]"
            "($cmdStr)".color(CMD_COLOR) + " " + "$checkStr $label".color(INTERACT_COLOR)
        }
        is ConsoleView.TextInput -> {
            val cmdStr = callbacks.registerNumberCallback {
                println("Напишите $label?")
                callbacks.registerAllCallback {
                    onEdit(it)
                }
            }
            "($cmdStr)".color(CMD_COLOR) + " " + "$label:".color(INTERACT_COLOR) + " " + value
        }
        is ConsoleView.PasswordInput -> {
            val cmdStr = callbacks.registerNumberCallback {
                println("Напишите $label?")
                callbacks.registerAllCallback {
                    onEdit(it)
                }
            }
            "($cmdStr)".color(CMD_COLOR) + " " + "$label:".color(INTERACT_COLOR) + " " + "********"
        }
        is ConsoleView.Spacer -> {
            ""
        }
    }
