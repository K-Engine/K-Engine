package org.kengine.scene.scripting.type

import org.kengine.scene.scripting.Script
import kotlin.properties.Delegates

/**
 * The base for any script that alters the behaviour of anything.
 */
abstract class BehaviourScript<T : Any> : Script<T> {
    override var parent: T by Delegates.notNull()

    override fun initialize() {}
    override fun update() {}
}