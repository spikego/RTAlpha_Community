package cn.rtalpha.mixin.unity;

import cn.rtalpha.event.EventBus;
import cn.rtalpha.event.events.KeyboardEvent;
import cn.rtalpha.event.events.MouseEvent;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.screen.ChatScreen;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {
    @Inject(method = "onMouseButton",at = @At("HEAD"))
    public void mouse(long window, int button, int action, int mods, CallbackInfo ci) {
        if(action == GLFW.GLFW_PRESS && (!(MinecraftClient.getInstance().currentScreen instanceof ChatScreen))
        ) {
            EventBus.INSTANCE.post(new MouseEvent(button));
        }
    }
}
