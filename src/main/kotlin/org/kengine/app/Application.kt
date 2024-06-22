package org.kengine.app

import org.kengine.KEngine
import org.kengine.app.base.IApplication
import org.kengine.scripting.api.Time
import org.kengine.window.Window
import org.lwjgl.opengl.GL11.*

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

    internal fun beginUpdate() {
        while(!parentWindow.closeRequested) {
            Time.lastRenderBegin = Time.currentTime

            glClearColor(0f, 0f, 0f, 0f)
            glClear(GL_COLOR_BUFFER_BIT)

            update()

            parentWindow.update()

            Time.lastRenderEnd = Time.currentTime
            Time.deltaTime = Time.lastRenderEnd - Time.lastRenderBegin
            Time.lastRenderBegin = Time.deltaTime
        }
    }

    override fun update() {}
    override fun create() {}

    init {
        KEngine.initializeApplication(this)
    }
}