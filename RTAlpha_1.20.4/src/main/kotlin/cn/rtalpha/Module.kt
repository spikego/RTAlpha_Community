package cn.rtalpha

import cn.rtalpha.event.events.DrawEvent
import cn.rtalpha.value.AbstractSetting
import net.minecraft.block.entity.BeaconBlockEntity.playSound
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text
import java.util.concurrent.CopyOnWriteArrayList
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier

open class Module(var name: String, var description: String, var category: Any) {

    var enabled = false
    val settings = CopyOnWriteArrayList<AbstractSetting<*>>()
    var key = -1
    open val mc = MinecraftClient.getInstance()


    open fun onTick() {

    }


    open fun onEnable() {}
    open fun onDisable() {}
    open fun onDraw(event: DrawEvent) {}
    open fun enable() {
        this.enabled = true


        onEnable()
    }

    fun disable() {
        this.enabled = false

        onDisable()
    }
    open fun toggle() {
        if (enabled) {
            disable()
        }else {
            enable()
        }
    }

    fun bindKey(key: Int) {
        this.key = key
    }

    private fun playSound(sound: String) {
        val soundEvent = SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP
        mc.player!!.playSound(soundEvent, 1.0f, 1.0f)
    }

}

