package org.kengine.gl.buffer

import org.kengine.gl.GLObject
import org.lwjgl.opengl.GL15.*

/**
 * Represents an OpenGL buffer
 */
class GLBuffer(
    private val bufferType: Int
) : GLObject(
    glGenBuffers()
) {
    /**
     * Bind this buffer
     */
    fun bind() {
        glBindBuffer(bufferType, handle)
    }

    /**
     * Unbind this buffer
     */
    fun unbind() {
        glBindBuffer(bufferType, 0)
    }

    /**
     * Insert some data into this buffer.
     */
    fun insertData(data: FloatArray, mode: Int = GL_STATIC_DRAW) {
        bind()
        glBufferData(bufferType, data, mode)
        unbind()
    }

    /**
     * Insert some data into this buffer.
     */
    fun insertData(data: IntArray, mode: Int = GL_STATIC_DRAW) {
        bind()
        glBufferData(bufferType, data, mode)
        unbind()
    }

    /**
     * Insert some data into this buffer.
     */
    fun insertData(data: ShortArray, mode: Int = GL_STATIC_DRAW) {
        bind()
        glBufferData(bufferType, data, mode)
        unbind()
    }

    /**
     * Insert some data into this buffer.
     */
    fun insertData(data: DoubleArray, mode: Int = GL_STATIC_DRAW) {
        bind()
        glBufferData(bufferType, data, mode)
        unbind()
    }

    /**
     * Dispose this buffer
     */
    fun dispose() {
        glDeleteBuffers(handle)
    }
}