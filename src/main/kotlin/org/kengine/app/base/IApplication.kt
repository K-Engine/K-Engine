package org.kengine.app.base

import org.kengine.window.Window

/**
 * The base for any type of application
 */
interface IApplication {
    /**
     * Invoked when the graphics context has been initialized and is ready for methods to be called.
     */
    fun create()

    /**
     * Invoked when the application is globally updated.
     */
    fun update()
}