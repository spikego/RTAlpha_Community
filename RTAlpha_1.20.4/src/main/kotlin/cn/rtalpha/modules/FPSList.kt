package cn.rtalpha.modules

import cn.rtalpha.RTAlpha
import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.event.events.DrawEvent
import java.awt.Color

object FPSList: Module("FPSList", "Display FPS", Category.RENDER) {
    init {
        this.enabled = false
    }

    private var fps = 0
    private var frameCount = 0
    private var lastTime = System.currentTimeMillis()

     override fun onDraw(event: DrawEvent) {
        frameCount++
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastTime >= 1000) {
            fps = frameCount
            frameCount = 0
            lastTime = currentTime
        }

        val fpsText = "FPS: $fps"
        val fpsColor = Color.decode("#fffffff").rgb
        event.context.drawText(mc.textRenderer, fpsText, 300, 500, fpsColor, true)
    }
}
