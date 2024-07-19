package org.kengine.scripting.api

import org.joml.Matrix4f
import org.joml.Quaternionf
import org.joml.Vector3f
import org.kengine.scripting.impl.GameBehaviourScript

/**
 * The transformations applied to a Game Object.
 */
class TransformScript : GameBehaviourScript() {
    /**
     * The rotation of this transform
     */
    val rotation = Quaternionf()

    /**
     * The position of this transform
     */
    val position = Vector3f()

    /**
     * The scale of this transform
     */
    val scale = Vector3f()

    init {
        reset()
    }

    /**
     * Reset this transformation to its default values
     */
    fun reset() {
        scale.set(1f, 1f, 1f)
        position.set(0f, 0f, 0f)
    }

    /**
     * This transformation converted into a [Matrix4f]
     */
    val matrix: Matrix4f get() = toMatrix()

    /**
     * Convert this transform to a matrix.
     */
    fun toMatrix(): Matrix4f = Matrix4f().translationRotateScale(position, rotation, scale)
}