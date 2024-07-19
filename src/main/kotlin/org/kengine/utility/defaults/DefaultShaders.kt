package org.kengine.utility.defaults

import org.kengine.rendering.shader.ShaderManager.createProgram
import org.kengine.rendering.shader.util.ShaderType

// Default shaders that are re-used.

val DefaultShader = createProgram(
    "Default"
) {
    shader("/shaders/default.frag", ShaderType.Fragment)
    shader("/shaders/default.vert", ShaderType.Vertex)
}