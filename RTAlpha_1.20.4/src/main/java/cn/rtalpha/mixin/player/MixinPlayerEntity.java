package cn.rtalpha.mixin.player;

import cn.rtalpha.modules.NoClickDelay;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {
    @Inject(method ="getAttackCooldownProgress",at = @At("HEAD"))
    public void modifyAttackCooldown(CallbackInfoReturnable<Float> cir){
        if(NoClickDelay.INSTANCE.getEnabled()){
        cir.setReturnValue(0.0F);
    }
        }
    }

