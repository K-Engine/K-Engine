package org.kengine.gui

import imgui.ImFontConfig
import imgui.ImGui
import imgui.ImVec2
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw
import org.kengine.utility.io.ResourceReader
import org.kengine.window.Window


class ImGuiRenderer {
    private val glfw = ImGuiImplGlfw()
    private val gl3 = ImGuiImplGl3()

    fun init(window: Window) {
        ImGui.createContext()

        val io = ImGui.getIO()

        io.iniFilename = null

        val font = io.fonts.addFontFromMemoryTTF(
            ResourceReader.readToByteArray("/fonts/Inter-Regular.ttf"),
            16f,
        )

        io.setFontDefault(font)
        io.fonts.build()

        glfw.init(window.handle, true)
        gl3.init("#version 130")

        val style = ImGui.getStyle()

        style.tabRounding = 7f
        style.windowRounding = 7f

        ImGui.styleColorsDark(style)
    }

    fun newFrame() {
        glfw.newFrame()
        ImGui.newFrame()
    }

    fun render() {
        ImGui.render()
        val dd = ImGui.getDrawData()
        gl3.renderDrawData(dd)
    }

    fun shutdown() {
        glfw.dispose()
        gl3.dispose()
        ImGui.destroyContext()
    }
}