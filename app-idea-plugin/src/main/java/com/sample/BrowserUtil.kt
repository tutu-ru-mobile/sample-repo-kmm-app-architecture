package com.sample

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.impl.IdeFrameImpl
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.JTextField
import javax.swing.WindowConstants

const val SHOW_URL = false

interface IClosable {
    fun close()
}

fun openBrowserJBCeffOrDefault(url: String): IClosable {
        fun safeJBCeffSupported(): Boolean {
            try {
                return JBCefApp.isSupported()
            } catch (t: Throwable) {
                return false
            }
        }

        if (safeJBCeffSupported()) {
            val activeFrame = IdeFrameImpl.getActiveFrame() ?: throw Error("missing frame")
            val bounds = activeFrame.graphicsConfiguration.bounds
            val frame: JFrame = IdeFrameImpl()
            frame.title = "Web Browser - JCEF"
            frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
            frame.setBounds(
                bounds.width / 8,
                bounds.height / 8,
                bounds.width * 2 / 4,
                bounds.height * 4 / 4
            )
            frame.layout = BorderLayout()
            val browser = JBCefBrowser(url)

            frame.add(browser.component, BorderLayout.CENTER)
            frame.addWindowListener(object : WindowAdapter() {
                override fun windowClosed(e: WindowEvent) {
                    Disposer.dispose(browser)
                }
            })
            if(SHOW_URL) {
                val urlBar = JTextField(url)
                urlBar.addActionListener { event: ActionEvent? -> browser.loadURL(urlBar.text) }
                frame.add(urlBar, BorderLayout.NORTH)
            }
            frame.isVisible = true
            return object : IClosable {
                override fun close() {
                    frame.dispatchEvent(WindowEvent(frame, WindowEvent.WINDOW_CLOSING))
                }
            }
        } else {
            BrowserUtil.browse(url)
            return object : IClosable {
                override fun close() {}
            }
        }
    }
