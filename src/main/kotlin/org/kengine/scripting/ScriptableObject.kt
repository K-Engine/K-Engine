package org.kengine.scripting

import org.kengine.scripting.type.BehaviourScript

/**
 * The base for any object that uses the Scripting API.
 */
abstract class ScriptableObject<C : Any, T : BehaviourScript<C>> {
    val scripts = mutableMapOf<Class<out T>, T>()

    /**
     * An instance of [C] to be implemented by the target.
     */
    protected abstract val self: C

    /**
     * Register a new script.
     *
     * @param script An instance of the script
     */
    fun registerScript(script: T) {
        script.parent = self
        script.initialize()

        scripts[script::class.java] = script
    }

    /**
     * Get a registered script.
     *
     * @param S The type of the script
     *
     * @return The script or null if it doesn't exist.
     */
    inline fun <reified S : T> getScript(): S? {
        return scripts[S::class.java] as S?
    }

    // Helper methods

    internal fun updateAllScripts() {
        scripts.values.forEach { it.update() }
    }

    internal fun postUpdateAllScripts() {
        scripts.values.forEach { it.postUpdate() }
    }
}