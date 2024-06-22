package org.kengine.window

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.glViewport
import org.lwjgl.system.MemoryUtil.NULL

/**
 * Handles creation and lifecycle of the window.
 */
class Window internal constructor(
    var width: Int,
    var height: Int,
    private val initialTitle: String
) {
    /**
     * The handle of this GLFW window.
     */
    var handle: Long = 0
        private set

    /**
     * The title of this window, it is only meant for changing the title and not getting it.
     */
    var title: String
        get() = initialTitle
        set(value) = glfwSetWindowTitle(handle, value)

    /**
     * The focus state of this window.
     */
    val focused get() = glfwGetWindowAttrib(handle, GLFW_FOCUSED) == GLFW_TRUE

    /**
     * The close state of this window.
     */
    val closeRequested get() = glfwWindowShouldClose(handle)

    /**
     * Create the GLFW window.
     */
    fun createWindow() {
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2)
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE)

        handle = glfwCreateWindow(width, height, initialTitle, 0, 0)
        if (handle == NULL)
            error("GLFW window creation failed")

        glfwMakeContextCurrent(handle)
        glfwSwapInterval(1)

        initCallbacks()

        GL.createCapabilities()
        glViewport(0, 0, width, height)
    }

    fun update() {
        glfwSwapBuffers(handle)
        glfwPollEvents()
    }

    fun closeWindow() {
        glfwSetWindowShouldClose(handle, true)
    }

    private fun initCallbacks() {
        glfwSetFramebufferSizeCallback(handle) { _, width, height ->
            this.width = width
            this.height = height

            glViewport(0, 0, width, height)
        }
    }
}