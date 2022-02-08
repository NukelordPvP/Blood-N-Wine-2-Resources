package me.nukelord.bloodnwine.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.TrackedEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TrackedEntity.class)
public class MixinTrackedEntity {

    @Shadow private int tickCount;
    @Shadow @Final private Entity entity;

    @Redirect(method = "sendChanges", at = @At(value = "INVOKE", target = "Ljava/lang/Math;abs(I)I", ordinal = 4))
    private int lessRotationUpdatePacketsForMobs(int i) {
        if(this.tickCount % 20 == 0 || this.entity instanceof ServerPlayerEntity){
            return i;
        }
        return 0;
    }

}
