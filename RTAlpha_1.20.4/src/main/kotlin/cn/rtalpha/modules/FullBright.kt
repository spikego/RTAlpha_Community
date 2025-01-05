package cn.rtalpha.modules

import cn.rtalpha.Category
import cn.rtalpha.Module
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects

object FullBright: Module("Fullbright", "赋予玩家夜视效果", Category.RENDER) {
    init {
        this.enabled = false
    }

    override fun onEnable() {
        val player = mc.player ?: return
        if (!player.hasStatusEffect(StatusEffects.NIGHT_VISION)){
            player.addStatusEffect(StatusEffectInstance(StatusEffects.NIGHT_VISION, Int.MAX_VALUE, 0, false, false))
        }
    }

    override fun onDisable() {
        val player = mc.player ?: return
        player.removeStatusEffect(StatusEffects.NIGHT_VISION)
    }

    // 新增方法，在玩家重生时调用
    fun onPlayerRespawn() {
        val player = mc.player ?: return
        if (!player.hasStatusEffect(StatusEffects.NIGHT_VISION)){
            player.addStatusEffect(StatusEffectInstance(StatusEffects.NIGHT_VISION, Int.MAX_VALUE, 0, false, false))
        }
    }
}
