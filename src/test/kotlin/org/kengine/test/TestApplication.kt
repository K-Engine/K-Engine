package org.kengine.test

import org.kengine.app.type.GameApplication

class TestApplication : GameApplication() {
    init {
        // Don't do anything here!
    }

    override fun create() {
        println("Game has been created!")

        // Do everything here!
        // Initialize textures, models and buffers here!
    }

    override fun update() {
        // Do cool stuff!
    }
}