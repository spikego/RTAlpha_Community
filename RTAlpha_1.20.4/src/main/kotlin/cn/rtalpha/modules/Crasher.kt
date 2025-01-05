package cn.rtalpha.modules

import cn.rtalpha.RTAlpha
import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.event.events.DrawEvent
import java.awt.Color

object Crasher: Module("Crasher","Crash game",Category.CLIENT) {
    init {
        this.enabled=false
    }

    override fun onDraw(event: DrawEvent) {
        // 使用 Color 类来定义 RGB 颜色
        val color = Color(266, 266, 266)
        event.context.drawText(mc.textRenderer,"${RTAlpha::class.java.simpleName}v1.0.2",100,150,color, true)
    }
}

fun <TextRenderer> Any.drawText(textRenderer: TextRenderer?, s: String, i: Int, i1: Int, color: Color, b: Boolean) {

}
