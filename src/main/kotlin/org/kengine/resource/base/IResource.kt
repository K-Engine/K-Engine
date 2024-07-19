package org.kengine.resource.base

import java.nio.ByteBuffer

/**
 * The base for any kind of resource.
 */
internal interface IResource {
    /**
     * The identifier for this resource
     */
    val identifier: String

    /**
     * The path for this resource
     */
    val path: String

    /**
     * Read the resource to a [ByteBuffer]
     */
    fun readBytes(): ByteBuffer

    /**
     * Read the resource to a [String]
     */
    fun readString(): String

    /**
     * Reload this resource. Clear whatever is cached.
     */
    fun reload()
}