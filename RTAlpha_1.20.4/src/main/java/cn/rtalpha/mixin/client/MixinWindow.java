package cn.rtalpha.mixin.client;

import cn.rtalpha.RTAlpha;
import net.minecraft.client.util.Icons;
import net.minecraft.client.util.Window;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.InputStream;
import java.util.List;

@Mixin(Window.class)
public class MixinWindow {
    @Redirect(method = "setIcon",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Icons;getIcons(Lnet/minecraft/resource/ResourcePack;)Ljava/util/List;"))
    public List<InputSupplier<InputStream>> setIconf(Icons instance, ResourcePack resourcePack) {
        InputStream icon16x = RTAlpha.class.getResourceAsStream("/assets/RTAlpha/icon16x.png");
        InputStream icon32x = RTAlpha.class.getResourceAsStream("/assets/RTAlpha/icon32x.png");
        return List.of(() -> icon16x,() -> icon32x);
    }
}
