package org.kengine.rendering.texture

/**
 * A simple object to create and manage textures.
 */
object TextureManager {
    private val textures = mutableMapOf<String, Texture>()

    /**
     * Register a texture
     */
    fun register(name: String, texture: Texture) {
        textures[name] = texture
    }

    /**
     * Get a texture
     *
     * @param name The name of the texture
     */
    operator fun get(name: String) = textures[name]
}