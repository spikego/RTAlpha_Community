package cn.rtalpha

import cn.rtalpha.event.EventManager
import cn.rtalpha.gui.ScreenGui
import net.fabricmc.api.ModInitializer
import net.minecraft.client.gui.screen.Screen
import org.slf4j.LoggerFactory

object RTAlpha : ModInitializer {
    private val logger = LoggerFactory.getLogger("RTAlpha")
	lateinit var screen: Screen

	override fun onInitialize() {
		EventManager.init()
		ModuleManager
		screen = ScreenGui




	}
}