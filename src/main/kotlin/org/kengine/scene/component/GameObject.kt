package org.kengine.scene.component

import org.kengine.scene.Scene
import org.kengine.scripting.ScriptableObject
import org.kengine.scripting.impl.GameBehaviourScript

/**
 * Represents an object in the hierarchy of Game Objects.
 */
abstract class GameObject : ScriptableObject<GameObject, GameBehaviourScript>() {
    override val self: GameObject
        get() = this

    /**
     * A reference to the GameObject hierarchy this GameObject resides in.
     */
    lateinit var parent: Scene
        internal set
}
