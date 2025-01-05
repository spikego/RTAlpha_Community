package cn.rtalpha.command.commands

import cn.rtalpha.Module
import cn.rtalpha.ModuleManager
import cn.rtalpha.command.Command
import cn.rtalpha.command.CommandManager
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text

object BindCommand: Command("bind", arrayOf("No")) {
    override fun run(args: Array<out String>) {
        if (args.size < 2) {
            MinecraftClient.getInstance().player!!.sendMessage(Text.of("§l§e[RTAlpha]§cUsage: .bind <module> <key>"))
        } else {
            // 从 args 数组中获取第一个元素作为 module
            val moduleName = args[0]
            // 从 args 数组中获取第二个元素作为 key
            val key = args[1]

            // 获取所有已注册的模块
            val modules = ModuleManager.getModules()

            // 在这里处理绑定逻辑
            // 例如，你可以检查模块是否存在，并将其绑定到指定的键
            if (modules.any { it.name == moduleName }) {
                key.toIntOrNull()?.let { modules.find { it.name == moduleName }?.bindKey(it) }
                MinecraftClient.getInstance().player!!.sendMessage(Text.of("§l§e[RTAlpha]§aBound module $moduleName to key $key!"))
            } else {
                MinecraftClient.getInstance().player!!.sendMessage(Text.of("§l§e[RTAlpha]§cModule $moduleName not found!"))
            }
        }
    }
}
