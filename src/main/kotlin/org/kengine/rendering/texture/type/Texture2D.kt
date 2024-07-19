package org.kengine.rendering.texture.type

import org.kengine.rendering.texture.Texture
import org.kengine.resource.Resource
import org.kengine.utility.io.toByteBuffer
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL30.glGenerateMipmap
import org.lwjgl.stb.STBImage
import org.lwjgl.stb.STBImage.stbi_info_from_memory
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

    private var channels = 0

    private lateinit var buffer: ByteBuffer

    init {
        load()
    }

    private fun load() {
        MemoryStack.stackPush().use {
            val widthBuf = it.mallocInt(1)
            val heightBuf = it.mallocInt(1)
            val channelsBuf = it.mallocInt(1)

            stbi_info_from_memory(resource.readBytes(), widthBuf, heightBuf, channelsBuf)
            buffer = STBImage.stbi_load_from_memory(
                resource.readBytes(), widthBuf, heightBuf, channelsBuf, 4
            ) ?: error("Failed to load image from ${resource.path}")

            width = widthBuf.get()
            height = heightBuf.get()
            channels = channelsBuf.get()
        }

        bind()

        val format = if (channels == 3) {
            if (width and 3 != 0) glPixelStorei(GL_UNPACK_ALIGNMENT, 2 - (width and 1))
            GL_RGB
        } else {
            GL_RGBA
        }

        glTexImage2D(target, 0, format, width, height, 0, format, GL_UNSIGNED_BYTE, buffer)

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, if(mipmap) GL_NEAREST_MIPMAP_NEAREST else GL_NEAREST)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)

        if (mipmap) glGenerateMipmap(GL_TEXTURE_2D)

        unbind()
    }
}