package org.kengine.utility.io

import org.lwjgl.system.MemoryStack
import java.nio.ByteBuffer

/**
 * Convert this [ByteArray] to a [java.nio.ByteBuffer]
 */
fun ByteArray.toByteBuffer(stack: MemoryStack): ByteBuffer {
    val buf = stack.calloc(1024)
    buf.put(this)
    buf.flip()
    return buf
}