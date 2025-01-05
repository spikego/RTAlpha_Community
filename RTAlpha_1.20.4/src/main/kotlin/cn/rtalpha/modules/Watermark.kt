package cn.rtalpha.modules

import cn.rtalpha.RTAlpha
import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.event.events.DrawEvent
import java.awt.Color

object Watermark: Module("Watermark","watermark.",Category.RENDER) {
    init {
        this.enabled = true
    }

    private var hue = 0f

    override fun onDraw(event: DrawEvent) {
        val text = "${RTAlpha::class.java.simpleName}-C1"

        // Update hue for rainbow effect
        hue += 0.001f // Adjust speed as needed
        if (hue > 1.0f) hue = 0f

        // Convert HSB to RGB color

        // Draw text with rainbow color
        event.context.drawText(
            mc.textRenderer,
            text,
            100, // x position
            150, // y position
            -1,
            true // shadow
        )
    }
}
