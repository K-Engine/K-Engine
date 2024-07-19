package org.kengine.scripting.type

/**
 * A behaviour script that allows you to do rendering.
 */
abstract class RenderBehaviourScript<T : Any> : BehaviourScript<T>() {
    open fun render() {}
}