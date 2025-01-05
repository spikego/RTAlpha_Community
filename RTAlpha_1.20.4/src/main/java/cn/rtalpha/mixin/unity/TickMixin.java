package cn.rtalpha.mixin.unity;

import cn.rtalpha.event.EventBus;
import cn.rtalpha.event.events.TickEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author yuan
 */
@Mixin(MinecraftClient.class)
public class TickMixin {
	@Inject(at = @At("HEAD"), method = "tick")
	private void init(CallbackInfo info) {
		EventBus.INSTANCE.post(new TickEvent());
	}
}