package org.kengine.scene

import imgui.ImGui
import org.kengine.app.type.GameApplication
import org.kengine.rendering.scene.SceneRenderer
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

    /**
     * The renderer for this scene.
     */
    lateinit var renderer: SceneRenderer

    internal fun reInit() {
        this.renderer = SceneRenderer(this)

        scripts.clear() // Clear all scripts for re-init

        // Re-init scripts
        init()

        scripts.forEach { it.parent = this; it.initialize() }
    }

    internal fun render() {
        // Call global scene update
        update()

        // Update all scripts
        scripts.forEach { it.update() }

        // Render the scene
        renderer.renderScene()

        // Update all scripts
        scripts.forEach { it.postUpdate() }
    }

    // Helper functions
    fun registerScript(script: SceneBehaviourScript) = scripts.add(script)

    // Scene methods
    override fun init() {}
    override fun update() {}
    override fun destroy() {}
    override fun ui() {}

    companion object {
        /**
         * An empty scene that has no functionality
         * for when everything is being initialized.
         */
        internal val EmptyScene get() = object : Scene() {}
    }
}