package org.kengine.app.type

import org.kengine.app.Application
import org.kengine.app.ApplicationConfiguration
import org.kengine.gui.ImGuiRenderer
import org.kengine.scene.Scene

/**
 * Represents a game as an application.
 */
open class GameApplication(
    configuration: ApplicationConfiguration = ApplicationConfiguration.Default
) : Application(configuration) {
    /**
     * The currently active scene in this application.
     */
    protected var activeScene: Scene = Scene.EmptyScene
        private set

    /**
     * Display a new scene and destroy the previous scene.
     */
    fun displayScene(scene: Scene) {
        activeScene?.destroy()
        activeScene = scene

        // Run scene setup
        activeScene.parentApplication = this
        activeScene.reInit()
    }

    // All logic will be handled by the scene rather than the application
    final override fun update() {
        activeScene?.render()
    }
}