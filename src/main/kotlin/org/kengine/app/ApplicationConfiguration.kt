package org.kengine.app

/**
 * Represents configuration for an application or game.
 */
data class ApplicationConfiguration(
    val name: String,
    val windowWidth: Int,
    val windowHeight: Int,
) {
    companion object {
        internal val Default = ApplicationConfiguration(
            "Game", 800, 600
        )
    }
}