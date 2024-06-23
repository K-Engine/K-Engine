package org.kengine.rendering.shader.util

import org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER
import org.lwjgl.opengl.GL20.GL_VERTEX_SHADER
import org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER

/**
 * The type of the shader
 */
enum class ShaderType(
    val glHandle: Int
) {
    Vertex(GL_VERTEX_SHADER),
    Fragment(GL_FRAGMENT_SHADER),
    Geometry(GL_GEOMETRY_SHADER);
}