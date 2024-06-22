package org.kengine.test.scenes

import imgui.ImGui
import org.kengine.scene.Scene

class MyScene : Scene() {
    override fun ui() {
        ImGui.begin("FPS Statistics")

        ImGui.end()
    }
}