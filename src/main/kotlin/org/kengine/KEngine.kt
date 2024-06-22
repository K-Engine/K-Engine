package org.kengine

import org.kengine.app.Application
import org.kengine.app.base.IApplication
import org.kengine.window.Window
import org.lwjgl.glfw.GLFW.glfwInit
import org.lwjgl.glfw.GLFWErrorCallback

/**
 * The core class of the engine, meant to be used internally.
 */
object KEngine {
    private var hasGraphicsContextBeenInitialized = false

    fun createWindow(
        title: String,
        width: Int,
        height: Int,
    ) = Window(width, height, title)

    private fun initializeGraphicsContext() {
        if(hasGraphicsContextBeenInitialized) {
            return
        }

        hasGraphicsContextBeenInitialized = true

        GLFWErrorCallback.createPrint(System.err).set()

        if (!glfwInit())
            error("GLFW init failed")
    }

    internal fun initializeApplication(application: Application) {
        initializeGraphicsContext()

        val window = createWindow(
            application.configuration.name,
            application.configuration.windowWidth,
            application.configuration.windowHeight,
        )

        application.parentWindow = window

        window.createWindow()

        application.create()
        application.beginUpdate()
    }
}