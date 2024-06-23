package org.kengine.rendering.shader.util

import org.kengine.rendering.gl.GLObject
import org.kengine.rendering.shader.ShaderProgram
import org.kengine.utility.io.ResourceReader
import org.lwjgl.opengl.GL20.*

internal class Shader(
    type: ShaderType,
    private val owner: ShaderProgram,
    private val source: String
) : GLObject(
    glCreateShader(type.glHandle)
) {
    init { compile() }

    private fun compile() {
        glShaderSource(handle, source)
        glCompileShader(handle)

        if(glGetShaderi(handle, GL_COMPILE_STATUS) == GL_FALSE) {
            println(glGetShaderInfoLog(handle))
            throw RuntimeException("Failed to compile shader")
        }

        glAttachShader(owner.handle, handle)
    }

    fun dispose() {
        glDeleteShader(handle)
    }
}