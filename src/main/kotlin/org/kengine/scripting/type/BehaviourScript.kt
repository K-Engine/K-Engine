package org.kengine.scripting.type

import org.kengine.scripting.Script
import kotlin.properties.Delegates

/**
 * The base for any script that alters the behaviour of anything.
 */
abstract class BehaviourScript<T : Any> : Script<T> {
    override var parent: T by Delegates.notNull()

    override fun initialize() {}
    override fun update() {}
}