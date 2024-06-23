package org.kengine.rendering.shader

/**
 * Allows you to create and manage shaders.
 */
object ShaderManager {
    private val shaders = mutableMapOf<String, ShaderProgram>()

    /**
     * Create a new shader program
     *
     * @param name The name of this program
     * @param configurationDSL The configuration of this program
     */
    fun createProgram(name: String, configurationDSL: ShaderProgram.ShaderConfiguration.() -> Unit): ShaderProgram {
        shaders[name] = ShaderProgram(configurationDSL)
        return shaders[name]!!
    }

    /**
     * Get a shader program
     *
     * @param name Name of the program
     */
    operator fun get(name: String) = shaders[name]
}