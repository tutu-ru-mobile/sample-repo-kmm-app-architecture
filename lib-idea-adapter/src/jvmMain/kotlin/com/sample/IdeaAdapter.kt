package com.sample

data class IdeaPanel(val rows: MutableList<IdeaRow> = mutableListOf())
data class IdeaRow(val views: MutableList<IdeaView> = mutableListOf())

sealed class IdeaView {
    class Button(val label: String, val onClick: () -> Unit) : IdeaView()
    class Label(val str: String) : IdeaView()
    class Title(val str: String) : IdeaView()
    class CheckBox(val label: String, val value: Boolean, val onClick: () -> Unit) : IdeaView()
    class TextInput(val value: String, val onEdit: (String) -> Unit) : IdeaView()
    class PasswordInput(val value: String, val onEdit: (String) -> Unit) : IdeaView()
}

interface IdeaPanelBuilder : IdeaBaseBuilder {
    fun row(lambda: IdeaRowBuilder.() -> Unit)
}

interface IdeaRowBuilder : IdeaBaseBuilder {

}

interface IdeaBaseBuilder {
    fun button(label: String, onClick: () -> Unit)
    fun label(str: String)
    fun title(str: String)
    fun checkBox(label: String, value: Boolean, onClick: () -> Unit)
    fun textInput(value: String, onEdit: (String) -> Unit)
    fun passwordInput(value: String, onEdit: (String) -> Unit)
}

fun panelView(lambda: IdeaPanelBuilder.() -> Unit): IdeaPanel {
    val result = IdeaPanel()
    object : IdeaPanelBuilder {
        override fun row(lambda: IdeaRowBuilder.() -> Unit) {
            val row = IdeaRow()
            result.rows.add(row)
            object : IdeaRowBuilder {
                override fun button(label: String, onClick: () -> Unit) {
                    row.views.add(IdeaView.Button(label, onClick))
                }

                override fun label(str: String) {
                    row.views.add(IdeaView.Label(str))
                }

                override fun title(str: String) {
                    row.views.add(IdeaView.Title(str))
                }

                override fun checkBox(label: String, value: Boolean, onClick: () -> Unit) {
                    row.views.add(IdeaView.CheckBox(label, value, onClick))
                }

                override fun textInput(label: String, onEdit: (String) -> Unit) {
                    row.views.add(IdeaView.TextInput(label, onEdit))
                }

                override fun passwordInput(value: String, onEdit: (String) -> Unit) {
                    row.views.add(IdeaView.PasswordInput(value, onEdit))
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
