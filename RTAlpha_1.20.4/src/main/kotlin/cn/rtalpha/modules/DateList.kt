package cn.rtalpha.modules

import cn.rtalpha.RTAlpha
import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.event.events.DrawEvent
import java.time.LocalDate
import java.awt.Color

object DateList: Module("DateList", "List date", Category.RENDER) {
    init {
        this.enabled = false
    }

    override fun onDraw(event: DrawEvent) {
        // 获取当前日期
        val currentDate = LocalDate.now()
        // 格式化日期为 yyyy-MM-dd
        val formattedDate = currentDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        // 绘制日期
        // 将十六进制颜色代码转换为整数
        val blueColor = Color.decode("#0000FF").rgb
        event.context.drawText(mc.textRenderer, formattedDate, 750, 175, blueColor, true)
    }
}
