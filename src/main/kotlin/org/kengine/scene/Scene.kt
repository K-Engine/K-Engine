package org.kengine.scene

import org.kengine.app.type.GameApplication
import org.kengine.scene.base.IScene
import org.kengine.scripting.impl.SceneBehaviourScript

/**
 * The base for any scene. All scenes extend off this scene.
 */
abstract class Scene : IScene {
    private var scripts = mutableListOf<SceneBehaviourScript>()

    /**
     * The parent of this application.
     */
    lateinit var parentApplication: GameApplication

    internal fun reInit() {
        scripts.clear() // Clear all scripts for re-init

        // Re-init scripts
        init()

        scripts.forEach { it.parent = this; it.initialize() }
    }

    internal fun render() {
        // Call global game update
        update()

        // Update all scripts
        scripts.forEach { it.update() }

        // TODO: Render

        // Update all scripts
        scripts.forEach { it.postUpdate() }
    }

    // Helper functions
    fun registerScript(script: SceneBehaviourScript) = scripts.add(script)

    // Scene methods
    override fun init() {}
    override fun update() {}
    override fun destroy() {}

    companion object {
        /**
         * An empty scene that has no functionality
         * for when everything is being initialized.
         */
        internal val EmptyScene = object : Scene() {}
    }
}