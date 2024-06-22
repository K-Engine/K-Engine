package org.kengine.scripting.api

import org.lwjgl.glfw.GLFW.glfwGetTime

/**
 * Helper utility for retrieving time data.
 */
object Time {
    /**
     * The time since this application has started.
     */
    val currentTime: Float get() = glfwGetTime().toFloat()

    /**
     * The time it took to render a frame.
     */
    var deltaTime: Float = 0f
        internal set

    internal var lastRenderBegin = 0f
    internal var lastRenderEnd = 0f
}