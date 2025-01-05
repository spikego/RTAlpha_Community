package cn.rtalpha.gui

import cn.rtalpha.Category
import cn.rtalpha.ModuleManager
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import java.util.concurrent.CopyOnWriteArrayList

class Window(var category: Category,var x: Int,var y: Int) {
    var moduleButtons = CopyOnWriteArrayList<ModuleButton>()
    var width = 120
    var height = 15
    var dragging = false
    var dragX: Int = 0
    var dragY: Int = 0
    var totalHeight = 0
    var open = true

    init {
        ModuleManager.modules.filter { it.category == category }.forEach {
            moduleButtons.add(ModuleButton(it,x,y))
        }
    }




    fun render(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        if(dragging) {
            x = dragX + mouseX
            y = dragY + mouseY
        }
        // 使用半透明颜色填充窗口背景
        // 使用半透明颜色填充窗口背景
        context!!.fill(x, y, x + width, y + height, 0x7FFF00)

        context.drawText(MinecraftClient.getInstance().textRenderer, category.name.lowercase(), x + 3, y + 3,0x71FF00 , true)
        context!!.fill(x, y, x + width, y + height, 0x7FFF00)
        if(open) {
            totalHeight = y + height
            moduleButtons.forEach {
                it.render(context, mouseX, mouseY, delta)
                it.setLocation(x, totalHeight)
                totalHeight += 15
            }
        }
    }



    fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if(this.open) {
            moduleButtons.forEach { it.mouseClicked(mouseX, mouseY, button) }
        }
        if(!this.isHover(x,y,width.toDouble(),height.toDouble(),mouseX.toInt(), mouseY.toInt())) return false
        when(button) {
            0 -> {
                dragging = true
                dragX = x - mouseX.toInt()
                dragY = y - mouseY.toInt()
            }
            1 -> {
                open = !open
            }
        }

        return false
    }

    fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        dragging = false
        return false
    }

    fun isHover(x: Int, y: Int, w: Double, h: Double, mouseX: Int, mouseY: Int): Boolean {
        return (mouseX >= x * 1.0f && mouseX <= (x + w) * 1.0f && mouseY >= y * 1.0f) && mouseY <= (y + h) * 1.0f
    }
}