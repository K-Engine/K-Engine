package org.kengine.rendering.gl.util

import org.kengine.rendering.gl.GLObject
import org.lwjgl.opengl.GL30.*

/**
 * Represents a Vertex Array Object.
 */
class VAO : GLObject(
    glGenVertexArrays()
) {
    /**
     * Bind this vertex array.
     */
    fun bind() {
        glBindVertexArray(handle)
    }

    /**
     * Unbind this vertex array.
     */
    fun unbind() {
        glBindVertexArray(0)
    }

    /**
     * Dispose this vertex array.
     */
    fun dispose() {
        glDeleteVertexArrays(handle)
    }
}