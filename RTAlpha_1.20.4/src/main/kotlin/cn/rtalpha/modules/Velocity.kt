// Velocity.kt
package cn.rtalpha.modules

import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.value.NumberSetting
import org.lwjgl.glfw.GLFW

object Velocity : Module("Velocity", "Modifies knockback", Category.COMBAT) {

    val horizontal = NumberSetting("Horizontal", "Horizontal knockback percentage", 0.0, 100.0, 0.0, 1.0)
    val vertical = NumberSetting("Vertical", "Vertical knockback percentage", 0.0, 100.0, 0.0, 1.0)

    init {
        this.enabled = false
        this.key = GLFW.GLFW_KEY_V

        settings.add(horizontal)
        settings.add(vertical)
    }

    fun modifyVelocity(x: Double, y: Double, z: Double): Triple<Double, Double, Double> {
        if (!enabled) return Triple(x, y, z)

        return Triple(
            x * (horizontal.value / 100.0),
            y * (vertical.value / 100.0),
            z * (horizontal.value / 100.0)
        )
    }
}
