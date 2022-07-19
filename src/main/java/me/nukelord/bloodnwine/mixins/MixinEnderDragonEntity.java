package me.nukelord.bloodnwine.mixins;


import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.ChargingPlayerPhase;
import net.minecraft.entity.boss.dragon.phase.IPhase;
import net.minecraft.entity.boss.dragon.phase.LandingApproachPhase;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnderDragonEntity.class)
public abstract class MixinEnderDragonEntity {

    @Shadow public abstract PhaseManager getPhaseManager();


    @Redirect(method = "findClosestNode()I", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I"))
    private int controlDragonFlightHeight(int i1, int i2){
        return i2 < 15 ? i1 : i2 - 55;
    }

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/dragon/phase/IPhase;getFlySpeed()F"))
    private float fasterFlying(IPhase phase){
        float moreflyspeed = 10;
        if(phase instanceof ChargingPlayerPhase || phase instanceof LandingApproachPhase)
        {
            moreflyspeed = 20;
        }
        return phase.getFlySpeed() + moreflyspeed;
    }
}
