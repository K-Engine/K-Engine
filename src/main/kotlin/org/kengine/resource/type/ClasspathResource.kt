package org.kengine.resource.type

import org.kengine.resource.Resource
import org.kengine.resource.util.ResourceNotFoundException
import org.lwjgl.BufferUtils.createByteBuffer
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.ByteBuffer

/**
 * Represents a resource in the classpath.
 */
open class ClasspathResource(
    identifier: String,
    path: String
) : Resource(identifier, path) {
    private var cachedStream: InputStream? = null
    private var cachedBuff: ByteBuffer? = null

    override fun readBytes(): ByteBuffer {
        if (cachedBuff != null) return cachedBuff!!

        val bytes = stream().readBytes()
        val buffer = createByteBuffer(bytes.size)
        buffer.put(bytes)
        buffer.flip()

        cachedBuff = buffer

        return cachedBuff!!
    }

    override fun readString(): String {
        return reader().readText()
    }

    override fun reload() {
        cachedStream = null
    }

    private fun reader(): InputStreamReader {
        return stream().reader()
    }

    private fun stream(): InputStream {
        if(cachedStream != null) {
            return cachedStream!!
        }

        val stream = ClasspathResource::class.java.getResourceAsStream("/$path") ?:
            throw ResourceNotFoundException("Resource not found: /$path ($identifier)")

        cachedStream = stream

        return stream
    }
}