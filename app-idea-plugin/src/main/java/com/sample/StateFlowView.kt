package com.sample

import com.intellij.ui.layout.LayoutBuilder
import com.intellij.ui.layout.panel
import com.intellij.ui.tabs.TabInfo
import com.intellij.ui.tabs.impl.JBTabsImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import java.awt.BorderLayout
import java.awt.Component
import javax.swing.JPanel

fun <T> CoroutineScope.stateFlowView(
  layoutBuilder: LayoutBuilder,
  stateFlow: StateFlow<T>,
  renderState: LayoutBuilder.(T) -> Unit
) {
  val parentPanel = JPanel(BorderLayout())
  layoutBuilder.row {
    parentPanel()
  }
  launch {
    stateFlow.collectLatest { state ->

      withContext(Dispatchers.Main) {
        parentPanel.removeAll()
        parentPanel.add(
          panel {
            renderState(state)
          }
        )
      }

    }
  }

}

inline fun <reified T : Component> Component.findParent(): T? =
  parentSequence().mapNotNull { it as? T }.firstOrNull()

fun Component.parentSequence() = sequence {
  var current = parent
  while (current != null) {
    yield(current)
    current = current.parent
  }
}
