package org.kengine.rendering.texture.base

/**
 * The base for any texture
 */
internal interface ITexture {
    /**
     * Bind this texture
     */
    fun bind(unit: Int = 0)

    /**
     * Unbind this texture
     */
    fun unbind()
}