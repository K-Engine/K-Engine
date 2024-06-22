package org.kengine.app

import org.kengine.KEngine
import org.kengine.app.base.IApplication
import org.kengine.window.Window

/**
 * The base for any application or game made with KEngine.
 */
abstract class Application(
    val configuration: ApplicationConfiguration = ApplicationConfiguration.Default
) : IApplication {
    /**
     * The parent window everything is being rendered on.
     */
    lateinit var parentWindow: Window
        internal set

    override fun create() {}
    override fun update() {}

    internal fun beginUpdate() {
        while(!parentWindow.closeRequested) {
            update()

            parentWindow.update()
        }
    }

    init {
        KEngine.initializeApplication(this)
    }
}