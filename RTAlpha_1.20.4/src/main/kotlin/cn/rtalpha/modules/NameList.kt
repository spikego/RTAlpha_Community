package cn.rtalpha.modules

import cn.rtalpha.RTAlpha
import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.event.events.DrawEvent
import java.awt.Color

object NameList: Module("NameList", "Display player name", Category.RENDER) {
    init {
        this.enabled = false
    }

    override fun onDraw(event: DrawEvent) {
        // 获取玩家名称
        val playerName = mc.player?.getName()
        // 绘制玩家名称
        // 将十六进制颜色代码转换为整数
        val pinkColor = Color.decode("#FFC0CB").rgb
        event.context.drawText(mc.textRenderer, playerName, 300, 520, pinkColor, true)
    }
}
