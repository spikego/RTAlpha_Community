# **RTAlpha
#### runClient失败或者启动游戏失败，很可能是FastPlace.kt出现了问题
##### **runClient失败**
替换FastPlace.kt代码到
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
        .getDeclaredField("field_1752") // Intermediary mapping name
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

##### 启动游戏失败
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
        .getDeclaredField("field_1752") // Intermediary mapping name
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

由于技术问题只能保一个，暂时不修复
