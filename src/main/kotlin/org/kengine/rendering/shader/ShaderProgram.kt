package org.kengine.rendering.shader

import org.kengine.rendering.gl.GLObject
import org.kengine.rendering.shader.util.Shader
import org.kengine.rendering.shader.util.ShaderType
import org.kengine.utility.io.ResourceReader
import org.lwjgl.opengl.GL20.*

/**
 * Utility to create and configure shader programs
 */
class ShaderProgram(
    configurationDSL: ShaderConfiguration.() -> Unit
) : GLObject(
    glCreateProgram()
) {
    private val config = ShaderConfiguration(this)

    init {
        configurationDSL.invoke(config)

        link()
    }

    private fun link() {
        // Link the program
        glLinkProgram(handle)

        // Dispose all shaders
        config.shaders.forEach { it.dispose() }
    }

    // Utility

    /**
     * Bind this shader program
     */
    fun bind() {
        glUseProgram(handle)
    }

    /**
     * Unbind this shader program
     */
    fun unbind() {
        glUseProgram(0)
    }

    // Uniforms

    /**
     * Get the location of a uniform
     *
     * @param name The name of the uniform
     */
    fun uniformLocation(name: String) = glGetUniformLocation(handle, name)

    /**
     * Add a uniform to this shader program.
     */
    operator fun set(name: String, value: Int) {
        glUniform1i(uniformLocation(name), value)
    }

    /**
     * Add a uniform to this shader program.
     */
    operator fun set(name: String, value: Float) {
        glUniform1f(uniformLocation(name), value)
    }

    /**
     * Configuration for a shader, provides DSL to add a shader to the owner.
     */
    class ShaderConfiguration internal constructor(
        private val owner: ShaderProgram
    ) {
        internal val shaders = mutableListOf<Shader>()

        /**
         * Attach a new shader to this program.
         */
        fun shader(path: String, type: ShaderType) {
            shaders += Shader(type, owner, ResourceReader.readToString(path))
        }
    }
}