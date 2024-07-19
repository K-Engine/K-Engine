package org.kengine.rendering.scene

import org.kengine.scene.Scene

/**
 * Handles rendering of a scene and its associated game objects.
 *
 * This class does not handle updating of the game objects.
 */
class SceneRenderer(
    private val scene: Scene
) {

    /**
     * Render the associated scene.
     */
    fun renderScene() {
        scene.renderAllScripts()
        scene.gameObjects.forEach { it.renderAllScripts() }

        // UI Must render over everything else
        renderUI()
    }

    private fun renderUI() {
        val imGuiRenderer = scene.application.window.imGuiRenderer

        // Build UI
        imGuiRenderer.newFrame()
        scene.ui()

        // Flush to viewport
        imGuiRenderer.render()
    }
}