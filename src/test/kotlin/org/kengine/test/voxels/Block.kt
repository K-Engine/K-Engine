package org.kengine.test.voxels

import org.joml.Vector3i

class Block(
    val id: Byte,
    val name: String
) {
    companion object {
        val blocks = mutableMapOf<Byte, Block>()

        // index to store the current block id
        private var idx = 0

        fun block(name: String): Block {
            val block = Block(idx.toByte(), name)
            blocks[idx.toByte()] = block

            // Increment the block index
            idx++

            return block
        }
    }
}

class BuiltBlock(
    val owner: Byte,
    val position: Vector3i,
    val parent: Chunk
) {
    val absolute = parent.position.add(position)
}