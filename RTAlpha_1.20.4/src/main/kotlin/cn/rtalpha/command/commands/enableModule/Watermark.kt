package cn.rtalpha.command.commands.enableModule

import cn.rtalpha.command.Command
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text

object Watermark: Command("watermark", arrayOf("No"))
{
    override fun run(args: Array<out String>) {
        MinecraftClient.getInstance().player!!.sendMessage(Text.of("§l§e[RTAlpha]§fCommand is OK!"))
    }
}