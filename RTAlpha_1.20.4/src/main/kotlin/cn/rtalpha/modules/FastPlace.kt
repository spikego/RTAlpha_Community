// FastPlace.kt
package cn.rtalpha.modules

import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.value.NumberSetting
import org.lwjgl.glfw.GLFW
import net.minecraft.client.MinecraftClient

object FastPlace : Module("FastPlace", "Places blocks faster", Category.WORLD) {
    init {
        this.enabled = false
        this.key = GLFW.GLFW_KEY_J
    }

    // Setting for place delay (in ticks)
    private val delay = NumberSetting("Delay", "Place delay in ticks", 0.0, 4.0, 1.0, 1.0)

    // Store the field reference using Yarn mappings for 1.20.4
    private val itemUseCooldownField = MinecraftClient::class.java
        .getDeclaredField("itemUseCooldown") // Intermediary mapping name
        //游戏启动失败就field_1752,runClient就itemUseCooldown
        .apply { isAccessible = true }

    init {
        settings.add(delay)
    }

    override fun onTick() {
        if (!enabled) return

        try {
            val mc = MinecraftClient.getInstance()
            // Set the cooldown using reflection
            itemUseCooldownField.setInt(mc, delay.value.toInt())
        } catch (e: Exception) {
            // Handle any reflection errors
            println("FastPlace error: ${e.message}")
        }
    }
}