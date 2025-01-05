package cn.rtalpha.modules

import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.event.events.DrawEvent
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.math.Vec3d
import org.joml.Matrix4f
import java.awt.Color

object NameTags : Module("NameTags", "Display all player nametag", Category.RENDER) {
    init {
        this.enabled = false
    }

    override fun onDraw(event: DrawEvent) {
        if (!enabled) return

        // Get all players in the world
        mc.world?.players?.forEach { player ->
            if (player != mc.player) { // Don't render nametag for the client player
                renderNameTag(player)
            }
        }
    }

    private fun renderNameTag(player: PlayerEntity) {
        // Get player position
        val pos = player.pos
        val d = pos.subtract(mc.gameRenderer.camera.pos)
        val distance = d.length()

        // Don't render if too far away
        if (distance > 64) return

        val matrices = MatrixStack()
        matrices.push()

        // Position the nametag above the player
        matrices.translate(
            pos.x - mc.gameRenderer.camera.pos.x,
            pos.y - mc.gameRenderer.camera.pos.y + player.height + 0.5,
            pos.z - mc.gameRenderer.camera.pos.z
        )

        // Make the nametag face the player
        matrices.multiply(mc.gameRenderer.camera.rotation)

        // Scale based on distance
        val scale = 0.025f * (distance * 0.3f).coerceAtLeast(1.0)
        matrices.scale((-scale).toFloat(), (-scale).toFloat(), scale.toFloat())

        val matrix4f = matrices.peek().positionMatrix

        // Create the text to display
        val text = Text.literal("${player.name.string} [${player.health.toInt()}❤]")

        // Get text dimensions
        val textWidth = mc.textRenderer.getWidth(text)

        // Draw the text
        val color = getPlayerColor(player)
        mc.textRenderer.draw(
            text,
            -textWidth / 2f,
            0f,
            color,
            false,
            matrix4f,
            mc.bufferBuilders.entityVertexConsumers,
            TextRenderer.TextLayerType.NORMAL,
            0,
            15728880
        )

        matrices.pop()
    }

    private fun getPlayerColor(player: PlayerEntity): Int {
        return when {
            player.isInvisible -> Color(255, 255, 0).rgb // Yellow for invisible players
            !player.isMainPlayer -> Color(255, 255, 255).rgb // White for other players
            else -> Color(0, 255, 0).rgb // Green for team members
        }
    }
}
