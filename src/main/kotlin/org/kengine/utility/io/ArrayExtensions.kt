package org.kengine.utility.io

import org.lwjgl.system.MemoryStack
import java.nio.ByteBuffer
import java.nio.FloatBuffer

/**
 * Convert this [ByteArray] to a [java.nio.ByteBuffer]
 *
 * DONT USE!! ITS BROKEN!!
 */
fun ByteArray.toByteBuffer(stack: MemoryStack): ByteBuffer {
    val buf = stack.calloc(1024)
    buf.put(this)
    buf.flip()
    return buf
}

/**
 * Convert this [FloatArray] to a [java.nio.FloatBuffer]
 */
fun FloatArray.toFloatBuffer(stack: MemoryStack): FloatBuffer {
    val buf = stack.callocFloat(this.size)
    buf.put(0, this)
    return buf
}