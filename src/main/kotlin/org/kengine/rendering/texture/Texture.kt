package org.kengine.rendering.texture

import org.kengine.rendering.gl.GLObject
import org.kengine.rendering.texture.base.ITexture
import org.lwjgl.opengl.GL11.glBindTexture
import org.lwjgl.opengl.GL11.glGenTextures
import org.lwjgl.opengl.GL13.GL_TEXTURE0
import org.lwjgl.opengl.GL13.glActiveTexture

/**
 * Represents an OpenGL texture.
 */
abstract class Texture(val target: Int) : ITexture, GLObject(
    glGenTextures()
) {
    override fun bind(unit: Int) {
        glActiveTexture(GL_TEXTURE0 + unit)
        glBindTexture(target, unit)
    }

    override fun unbind() {
        glBindTexture(target, 0)
    }
}