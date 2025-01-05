package cn.rtalpha.modules

import cn.rtalpha.RTAlpha
import cn.rtalpha.Category
import cn.rtalpha.Module
import org.lwjgl.glfw.GLFW

object ClickGui: Module("Clickgui","click gui",Category.CLIENT) {
    init {
        this.key = GLFW.GLFW_KEY_RIGHT_SHIFT
    }
    override fun onEnable() {
        mc.setScreen(RTAlpha.screen)
    }
}