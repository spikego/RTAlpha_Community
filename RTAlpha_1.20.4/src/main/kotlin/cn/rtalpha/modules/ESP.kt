// ESP.kt
package cn.rtalpha.modules

import cn.rtalpha.RTAlpha
import cn.rtalpha.Category
import cn.rtalpha.Module
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.WorldRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Box
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents
import net.minecraft.client.MinecraftClient

object ESP: Module("ESP", "PlayerESP", Category.RENDER) {
    init {
        WorldRenderEvents.AFTER_ENTITIES.register { context ->
            if (enabled) {
                renderESP(context)
            }
        }
    }

    private fun renderESP(context: WorldRenderContext) {
        val matrices = context.matrixStack()
        val vertexConsumers = context.consumers() ?: return
        val mc = MinecraftClient.getInstance()

        mc.world?.entities?.forEach { entity ->
            if (entity is PlayerEntity && entity != mc.player) {
                // 直接使用实体的位置，忽略碰撞箱
                val x = entity.x
                val y = entity.y
                val z = entity.z

                matrices.push()
                matrices.translate(-mc.gameRenderer.camera.pos.x, -mc.gameRenderer.camera.pos.y, -mc.gameRenderer.camera.pos.z)

                // Draw box outline
                WorldRenderer.drawBox(
                    matrices,
                    vertexConsumers.getBuffer(RenderLayer.getLines()),
                    Box(x - 0.4, y, z - 0.4, x + 0.4, y + 1.8, z + 0.4),
                    1.0f,  // Red
                    0.0f,  // Green
                    0.0f,  // Blue
                    1.0f   // Alpha
                )

                matrices.pop()
            }
        }
    }
}
