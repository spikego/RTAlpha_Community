package cn.rtalpha.event.events

import net.minecraft.client.MinecraftClient
import net.minecraft.entity.effect.StatusEffectInstance

class PlayerEvent {
    private val mc: MinecraftClient = MinecraftClient.getInstance()

    fun isPlayerAlive(): Boolean {
        val player = mc.player ?: return false
        return !player.isDead
    }

    fun getPlayerPotionEffects(): List<StatusEffectInstance> {
        val player = mc.player ?: return emptyList()
        return player.statusEffects.toList()
    }
}