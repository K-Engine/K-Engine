package org.kengine.test

import org.kengine.app.type.GameApplication
import org.kengine.rendering.shader.ShaderManager.createProgram
import org.kengine.rendering.shader.util.ShaderType
import org.kengine.test.scenes.MyScene

class TestApplication : GameApplication() {
    init {
        // Don't do anything here!
    }

    override fun create() {
        println("Game has been created!")

        // Display our scene
        displayScene(MyScene())

        // Do everything here!
        // Initialize textures, models and buffers here!

        // Create a shader!
        createProgram("SceneProgram") {
            shader("/test.glsl", ShaderType.Fragment)
            shader("/test.v.glsl", ShaderType.Vertex)
        }
    }
}