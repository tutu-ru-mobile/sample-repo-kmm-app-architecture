package com.sample

data class ConsolePanel(
    val rows: MutableList<ConsoleRow> = mutableListOf(),
    val bottomRows: MutableList<ConsoleRow> = mutableListOf()
)

data class ConsoleRow(val views: MutableList<ConsoleView> = mutableListOf())

sealed class ConsoleView {
    class Button(val label: String, val onClick: () -> Unit) : ConsoleView()
    class Label(val str: String) : ConsoleView()
    class Title(val str: String) : ConsoleView()
    class CheckBox(val label: String, val value: Boolean, val onClick: () -> Unit) : ConsoleView()
    class TextInput(val value: String, val onEdit: (String) -> Unit) : ConsoleView()
    class PasswordInput(val value: String, val onEdit: (String) -> Unit) : ConsoleView()
}

interface ConsolePanelBuilder : ConsoleBaseBuilder {
    fun row(lambda: ConsoleRowBuilder.() -> Unit)
    fun bottomRow(lambda: ConsoleRowBuilder.() -> Unit)
}

interface ConsoleRowBuilder : ConsoleBaseBuilder {

}

interface ConsoleBaseBuilder {
    fun button(label: String, onClick: () -> Unit)
    fun label(str: String)
    fun title(str: String)
    fun checkBox(label: String, value: Boolean, onClick: () -> Unit)
    fun textInput(value: String, onEdit: (String) -> Unit)
    fun passwordInput(value: String, onEdit: (String) -> Unit)
}

fun consolePanelView(lambda: ConsolePanelBuilder.() -> Unit): ConsolePanel {
    val result = ConsolePanel()
    object : ConsolePanelBuilder {
        override fun row(lambda: ConsoleRowBuilder.() -> Unit) {
            val row = ConsoleRow()
            result.rows.add(row)
            object : ConsoleRowBuilder {
                override fun button(label: String, onClick: () -> Unit) {
                    row.views.add(ConsoleView.Button(label, onClick))
                }

                override fun label(str: String) {
                    row.views.add(ConsoleView.Label(str))
                }

                override fun title(str: String) {
                    row.views.add(ConsoleView.Title(str))
                }

                override fun checkBox(label: String, value: Boolean, onClick: () -> Unit) {
                    row.views.add(ConsoleView.CheckBox(label, value, onClick))
                }

                override fun textInput(label: String, onEdit: (String) -> Unit) {
                    row.views.add(ConsoleView.TextInput(label, onEdit))
                }

                override fun passwordInput(value: String, onEdit: (String) -> Unit) {
                    row.views.add(ConsoleView.PasswordInput(value, onEdit))
                }

            }.lambda()
        }

        override fun bottomRow(lambda: ConsoleRowBuilder.() -> Unit) {
            val row = ConsoleRow()
            result.bottomRows.add(row)
            object : ConsoleRowBuilder {
                override fun button(label: String, onClick: () -> Unit) {
                    row.views.add(ConsoleView.Button(label, onClick))
                }

                override fun label(str: String) {
                    row.views.add(ConsoleView.Label(str))
                }

                override fun title(str: String) {
                    row.views.add(ConsoleView.Title(str))
                }

                override fun checkBox(label: String, value: Boolean, onClick: () -> Unit) {
                    row.views.add(ConsoleView.CheckBox(label, value, onClick))
                }

                override fun textInput(label: String, onEdit: (String) -> Unit) {
                    row.views.add(ConsoleView.TextInput(label, onEdit))
                }

                override fun passwordInput(value: String, onEdit: (String) -> Unit) {
                    row.views.add(ConsoleView.PasswordInput(value, onEdit))
                }

            }.lambda()
        }

        override fun button(label: String, onClick: () -> Unit) {
            row {
                button(label, onClick)
            }
        }

        override fun label(str: String) {
            row {
                label(str)
            }
        }

        override fun title(str: String) {
            row {
                title(str)
            }
        }

        override fun checkBox(label: String, value: Boolean, onClick: () -> Unit) {
            row {
                checkBox(label, value, onClick)
            }
        }

        override fun textInput(value: String, onEdit: (String) -> Unit) {
            row {
                textInput(value, onEdit)
            }
        }

        override fun passwordInput(value: String, onEdit: (String) -> Unit) {
            row {
                passwordInput(value, onEdit)
            }
        }

    }.lambda()
    return result
}
