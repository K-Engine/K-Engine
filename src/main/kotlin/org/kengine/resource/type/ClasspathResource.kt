package org.kengine.resource.type

import org.kengine.resource.Resource
import org.kengine.resource.util.ResourceNotFoundException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Represents a resource in the classpath.
 */
open class ClasspathResource(
    identifier: String,
    path: String
) : Resource(identifier, path) {
    private var cachedStream : InputStream? = null

    override fun readBytes(): ByteArray {
        return stream().readBytes()
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