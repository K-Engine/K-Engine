package org.kengine.rendering.texture.type

import org.kengine.rendering.texture.Texture
import org.kengine.resource.Resource
import org.kengine.utility.io.toByteBuffer
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL30.glGenerateMipmap
import org.lwjgl.stb.STBImage
import org.lwjgl.system.MemoryStack
import java.nio.ByteBuffer

/**
 * Represents a 2D texture.
 */
class Texture2D(
    private val resource: Resource,
    private val mipmap: Boolean = false
) : Texture(GL_TEXTURE_2D) {
    /**
     * The width of this texture
     */
    var width = 0
        private set

    /**
     * The height of this texture
     */
    var height = 0
        private set

    init {
        load()
    }

    private fun load() {
        var buffer: ByteBuffer

        MemoryStack.stackPush().use {
            val widthBuf = it.mallocInt(1)
            val heightBuf = it.mallocInt(1)
            val channelsBuf = it.mallocInt(1)

            buffer = STBImage.stbi_load_from_memory(
                resource.readBytes().toByteBuffer(it), widthBuf, heightBuf, channelsBuf, 4
            ) ?: error("Failed to load image from ${resource.path}")

            width = widthBuf.get()
            height = heightBuf.get()
        }

        bind()

        glTexImage2D(target, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer)

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, if(mipmap) GL_NEAREST_MIPMAP_NEAREST else GL_NEAREST)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)

        if (mipmap) glGenerateMipmap(GL_TEXTURE_2D)

        unbind()
    }
}