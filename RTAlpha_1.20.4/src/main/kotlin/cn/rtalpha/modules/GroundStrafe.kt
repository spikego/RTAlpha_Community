package cn.rtalpha.modules

import cn.rtalpha.Category
import cn.rtalpha.Module
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import org.lwjgl.glfw.GLFW

object GroundStrafe : Module("GroundStrafe", "Allows you to strafe left, right, forward, and back on the ground with custom speed settings and auto-jump.", Category.MOVEMENT) {

    override val mc: MinecraftClient = MinecraftClient.getInstance()
    private val speed = 0.281

    init {
        this.enabled = false
        this.key = GLFW.GLFW_KEY_V
    }

    override fun onTick() {

        val player: ClientPlayerEntity = mc.player ?: return

        if (!player.isOnGround) {
            return
        } else {
            if (mc.options.leftKey.isPressed && !mc.options.forwardKey.isPressed) {
                val yaw = Math.toRadians(player.yaw.toDouble() - 90)
                player.addVelocity(-Math.sin(yaw) * speed, 0.0, Math.cos(yaw) * speed)
            }
            if (mc.options.rightKey.isPressed && !mc.options.forwardKey.isPressed) {
                val yaw = Math.toRadians(player.yaw.toDouble() + 90)
                player.addVelocity(-Math.sin(yaw) * speed, 0.0, Math.cos(yaw) * speed)
            }
            if (mc.options.backKey.isPressed && !mc.options.rightKey.isPressed && !mc.options.leftKey.isPressed) {
                val yaw = Math.toRadians(player.yaw.toDouble() + 180)
                player.addVelocity(-Math.sin(yaw) * speed, 0.0, Math.cos(yaw) * speed)
            }


            if (mc.options.leftKey.isPressed || mc.options.rightKey.isPressed || mc.options.backKey.isPressed || mc.options.forwardKey.isPressed) {
                player.jump()
            }
        }
    }
}