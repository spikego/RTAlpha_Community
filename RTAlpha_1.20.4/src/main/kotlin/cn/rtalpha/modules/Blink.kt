package cn.rtalpha.modules

import cn.rtalpha.Category
import cn.rtalpha.Module
import net.minecraft.network.packet.Packet
import net.minecraft.client.MinecraftClient
import org.lwjgl.glfw.GLFW

object Blink : Module("Blink", "Temporarily stops sending packets", Category.CLIENT) {
    init {
        this.enabled = false
        this.key= GLFW.GLFW_KEY_B
    }
    private val packetQueue = mutableListOf<Packet<*>>()
    override val mc: MinecraftClient = MinecraftClient.getInstance()

    init {
        this.enabled = false
    }

    override fun onEnable() {
        // Start intercepting packets
        // Register event listeners for PacketEvent.Send and PacketEvent.Receive
    }

    override fun onDisable() {
        // Stop intercepting packets and send all queued packets
        packetQueue.forEach { mc.networkHandler?.sendPacket(it) }
        packetQueue.clear()
    }

    fun onPacketSend(event: PacketEvent.Send) {
        if (this.enabled) {
            packetQueue.add(event.packet)
            event.cancel() // Prevent the packet from being sent
        }
    }

    fun onPacketReceive(event: PacketEvent.Receive) {
        if (this.enabled) {
            // Handle received packets if necessary
        }
    }
}