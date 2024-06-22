package org.kengine.rendering.scene

import org.kengine.scene.Scene

/**
 * Used to contents of scenes and the associated game objects.
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
        val imGuiRenderer = scene.parentApplication.parentWindow.imGuiRenderer

        // Render UI
        imGuiRenderer.newFrame()
        scene.ui()

        // Flush to viewport
        imGuiRenderer.render()
    }
}