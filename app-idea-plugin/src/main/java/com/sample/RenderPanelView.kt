package com.sample

import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBPasswordField
import com.intellij.ui.components.JBTextField
import com.intellij.ui.layout.LayoutBuilder

fun renderPanelView(
  layoutBuilder: LayoutBuilder,
  project: Project,
  panelView: IdeaPanel
) {
  panelView.rows.forEach { r ->
    layoutBuilder.row {
      cell {
        r.views.forEach { v ->
          when (v) {
            is IdeaView.Label -> {
              label(v.str)
            }
            is IdeaView.Title -> {
              label(v.str, bold = true)
            }
            is IdeaView.Button -> {
              button(v.label) {
                v.onClick()
              }
            }
            is IdeaView.CheckBox -> {
              checkBox(
                text = v.label,
                isSelected = v.value
              ) { _, _ ->
                v.onClick()
              }
            }
            is IdeaView.TextInput -> {
//              textField(
//                getter = { v.value },
//                setter = { v.onEdit(it) }
//              )
              val jbTextField = JBTextField()
              jbTextField.text = v.value
              jbTextField.onTextChange {
                v.onEdit(it)
              }
              jbTextField()
            }
            is IdeaView.PasswordInput -> {
              val jbPasswordField = JBPasswordField()
              jbPasswordField.text = v.value
              jbPasswordField.onTextChange {
                v.onEdit(it)
              }
              jbPasswordField()
            }
          }
        }
      }
    }
  }

  layoutBuilder.hideableRow("") {
    cell {
      textFieldCompletion(
        project = project,
        label = null,
        currentValue = "a",
        autoCompletionVariants = listOf("aaa", "aab", "abb", "aba")
      ) {}
    }
  }

}
