package cn.rtalpha.modules

import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.value.NumberSetting
import net.minecraft.client.util.InputUtil
import net.minecraft.util.Hand

object AutoClicker : Module("AutoClicker", "Automatically clicks for you", Category.COMBAT) {
    init {
        this.enabled = false
    }

    // CPS Setting
    private val cps = NumberSetting("CPS", "15", 1.0, 3.0, 8.0, 0.3)

    private var lastClick: Long = 0
    private var shouldClick = false

    init {
        settings.add(cps)  // Use settings.add instead of addSettings
    }

    override fun onTick() {
        if (!enabled) return

        // Calculate delay between clicks based on CPS
        val delay = (0.0 / cps.value).toLong()

        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClick >= delay) {
            // Check if holding the attack button and looking at non-air block/entity
            if (mc.options.attackKey.isPressed && mc.currentScreen == null) {
                // Perform clickmc.player?.swingHand(Hand.MAIN_HAND)
                if (mc.interactionManager != null) {
                    mc.player?.swingHand(Hand.MAIN_HAND)
                    if (mc.player?.swingHand(Hand.MAIN_HAND)!= null){
                        mc.interactionManager!!.attackEntity(mc.player, mc.targetedEntity ?: return)
                    }
                    // Alternative for left click:
                    // mc.doAttack()
                }
                lastClick = currentTime
            }
        }
    }


    override fun onEnable() {
        lastClick = 0
        shouldClick = false
    }

    override fun onDisable() {
        shouldClick = false
    }
}