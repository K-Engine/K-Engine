package org.kengine.scene

import org.joml.Matrix4f
import org.kengine.app.type.GameApplication
import org.kengine.rendering.scene.SceneRenderer
import org.kengine.scene.base.IScene
import org.kengine.scene.component.GameObject
import org.kengine.scripting.ScriptableObject
import org.kengine.scripting.impl.SceneBehaviourScript
import javax.swing.Spring.height



/**
 * The base for any scene. All scenes extend off this scene.
 */
abstract class Scene : IScene, ScriptableObject<Scene, SceneBehaviourScript>() {
    /**
     * The parent of this application.
     */
    lateinit var application: GameApplication

    /**
     * The GameObject hierarchy.
     */
    val gameObjects = mutableListOf<GameObject>()

    /**
     * The projection matrix of this scene.
     */
    val projectionMatrix = Matrix4f()

    protected var fov = Math.toRadians(60.0)
    protected var zFar = 1000f
    protected var zNear = .01f

    override val self: Scene
        get() = this
    
    /**
     * The renderer for this scene.
     */
    lateinit var renderer: SceneRenderer

    internal fun reInit() {
        this.renderer = SceneRenderer(this)

        scripts.clear() // Clear all scripts for re-init
        gameObjects.clear() // Clear all GOs for re-init

        // Re-init scripts and GOs
        init()

        scripts.values.forEach { it.parent = this; it.initialize() }
    }

    internal fun render() {
        projectionMatrix.setPerspective(
            fov.toFloat(),
            application.window.width / application.window.height.toFloat(),
            zNear, zFar
        )

        // Call global scene update
        update()

        // Update all scripts
        updateAllScripts()
        gameObjects.forEach { it.updateAllScripts() }

        // Render the scene
        renderer.renderScene()

        // Update all scripts
        postUpdateAllScripts()
        gameObjects.forEach { it.postUpdateAllScripts() }
    }

    // Scene methods
    override fun init() {}
    override fun update() {}
    override fun destroy() {}
    override fun ui() {}

    /**
     * Register a new game object into the hierarchy
     */
    fun registerGameObject(gameObject: GameObject) {
        gameObject.parent = this

        gameObjects += gameObject
    }

    /**
     * Find a game object
     *
     * @param name The name of the Game Object
     *
     * @return The game object or null
     */
    fun findGameObject(name: String): GameObject? {
        return gameObjects.firstOrNull { it.name == name }
    }

    /**
     * Register a new game object into the hierarchy
     */
    fun registerGameObject(gameObject: () -> GameObject) {
        registerGameObject(gameObject())
    }

    companion object {
        /**
         * An empty scene that has no functionality
         * for when everything is being initialized.
         */
        internal val EmptyScene get() = object : Scene() {}
    }
}