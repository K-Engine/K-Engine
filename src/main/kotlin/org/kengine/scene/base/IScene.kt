package org.kengine.scene.base

import imgui.ImGui

/**
 * The base for any scene
 */
interface IScene {
    /**
     * Invoked when this scene is initialized.
     */
    fun init()

    /**
     * Invoked when this scene is updated.
     */
    fun update()

    /**
     * Invoked when this scene is destroyed.
     * Dispose temporary resources here.
     */
    fun destroy()

    /**
     * Invoked when the UI is ready to be rendered.
     */
    fun ui()
}