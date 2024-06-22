package org.kengine.scene.scripting

/**
 * The base for any script, defines basic methods.
 */
interface Script<T : Any> {
    /**
     * The parent of this script.
     * @see org.kengine.scene.scripting.impl.SceneBehaviourScript
     */
    var parent: T

    /**
     * Invoked when the Script is re-initialized.
     */
    fun initialize()

    /**
     * Invoked before rendering the current scene.
     */
    fun update()

    /**
     * Invoked when the Game has finished rendering the current scene.
     */
    fun postUpdate()
}