package com.sample

import com.intellij.ui.EditorTextField
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import javax.swing.text.JTextComponent

fun JTextComponent.onTextChange(
  onEdit: (String) -> Unit
) {
  val currentValue = text
  addFocusListener(object : FocusListener {
    override fun focusLost(e: FocusEvent?) {
      val newValue: String = text
      if (currentValue != newValue) {
        onEdit(newValue)
      }
    }

    override fun focusGained(e: FocusEvent?) {}
  })
}

fun EditorTextField.onTextChange(
  onEdit: (String) -> Unit
) {
  val currentValue = text
  addFocusListener(object : FocusListener {
    override fun focusLost(e: FocusEvent?) {
      val newValue: String = text
      if (currentValue != newValue) {
        onEdit(newValue)
      }
    }

    override fun focusGained(e: FocusEvent?) {}
  })
}