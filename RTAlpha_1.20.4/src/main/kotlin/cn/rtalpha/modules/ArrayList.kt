package cn.rtalpha.modules

import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.ModuleManager
import cn.rtalpha.event.events.DrawEvent
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.White

object ArrayList: Module("ArrayList","array list",Category.RENDER) {
    init {
        this.enabled=true
    }

    override fun onDraw(event: DrawEvent) {
        var y = 160
        ModuleManager.modules.filter { it.enabled }.forEach {
            event.context.drawTextWithShadow(mc.textRenderer,it.name,100,y,0xFFFFFF)
            y += mc.textRenderer.fontHeight + 1
        }
    }
}