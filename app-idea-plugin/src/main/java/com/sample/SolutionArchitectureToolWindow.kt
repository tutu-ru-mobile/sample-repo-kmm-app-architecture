package com.sample

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.layout.panel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SolutionArchitectureToolWindow : ToolWindowFactory, DumbAware {
  override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
    MainScope().launch {
      val adapter = getStateFlowAndRenderAdapter()
      toolWindow.contentManager.removeAllContents(true)
      toolWindow.contentManager.addContent(
        ContentFactory.SERVICE.getInstance().createContent(
          panel {
            stateFlowView(this, adapter) {
              renderPanelView(this, ProjectManager.getInstance().defaultProject, it)
            }
          }, "", false
        )
      )
    }
  }
}
