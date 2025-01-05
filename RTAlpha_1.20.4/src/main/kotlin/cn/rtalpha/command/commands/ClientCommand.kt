package cn.rtalpha.command.commands

import cn.rtalpha.command.Command
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text

object ClientCommand: Command("client", arrayOf("No"))
{
    override fun run(args: Array<out String>) {
        MinecraftClient.getInstance().player!!.sendMessage(Text.of("§l§e[RTAlpha]§aclient:RTAlpha,§bversion:1.0.2"))
    }
}