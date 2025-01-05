package cn.rtalpha.modules

import cn.rtalpha.RTAlpha
import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.event.events.DrawEvent
import java.time.LocalTime
import java.awt.Color

object Timelist: Module("TimeList","List time",Category.RENDER) {
    init {
        this.enabled=false
    }

    override fun onDraw(event: DrawEvent) {
        // 获取当前时间
        val currentTime = LocalTime.now()
        // 格式化时间为 HH:mm
        val formattedTime = currentTime.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))
        // 绘制时间
        // 将十六进制颜色代码转换为整数
        val pinkColor = Color.decode("#FFC0CB").rgb
        event.context.drawText(mc.textRenderer, formattedTime, 750, 150, pinkColor, true)
    }
}
