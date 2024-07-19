package org.kengine.test.voxels

import org.joml.Vector3f
import org.joml.Vector3i
import org.kengine.test.voxels.Block.Companion.blocks
import kotlin.random.Random

class Chunk(
    val position: Vector3i
) {
    val random = Random(0L)

    val data: Array<Array<Array<BuiltBlock>>> = arrayOf(arrayOf(arrayOf()))

    init {
        for(x in 0..16) {
            for(y in 0..16) {
                for(z in 0..16) {
                    val relativePosition = Vector3i(x, y, z)
                    val absolutePosition = position.add(relativePosition)

                    val block = blocks[random.nextInt(0, blocks.size - 1).toByte()]!!

                    data[x][y][z] = BuiltBlock(block.id, relativePosition, this)
                }
            }
        }
    }
}