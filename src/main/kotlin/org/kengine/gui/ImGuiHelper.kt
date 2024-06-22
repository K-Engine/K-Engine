package org.kengine.gui

import imgui.ImGui
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw
import org.kengine.window.Window

internal object ImGuiHelper {
    private val glfw = ImGuiImplGlfw()
    private val gl3 = ImGuiImplGl3()

    fun init(window: Window) {
        ImGui.createContext()

        val io = ImGui.getIO()
        io.fonts.addFontDefault()

        glfw.init(window.handle, true)
        gl3.init("#version 330")

        ImGui.styleColorsDark()
    }

    fun wantsControl() = ImGui.getIO().wantCaptureMouse

    fun newFrame() {
        glfw.newFrame()
        ImGui.newFrame()
    }

    fun render() {
        ImGui.render()
        gl3.renderDrawData(ImGui.getDrawData())
    }

    fun shutdown() {
        glfw.dispose()
        gl3.dispose()
        ImGui.destroyContext()
    }
}