package org.kengine.test

import io.github.oshai.kotlinlogging.KotlinLogging
import org.kengine.app.type.GameApplication
import org.kengine.rendering.shader.ShaderManager.createProgram
import org.kengine.rendering.shader.util.ShaderType
import org.kengine.rendering.texture.TextureManager
import org.kengine.rendering.texture.type.Texture2D
import org.kengine.resource.ResourceManager
import org.kengine.resource.type.ClasspathResource
import org.kengine.test.scenes.MyScene

private val LOGGER = KotlinLogging.logger {  }

class TestApplication : GameApplication() {
    init {
        // Don't do anything here!
    }

    override fun create() {
        // Display our scene!
        displayScene(MyScene())
    }
}