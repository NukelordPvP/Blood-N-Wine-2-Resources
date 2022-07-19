package me.nukelord.bloodnwine.mixins;

import net.minecraft.entity.boss.dragon.phase.LandingApproachPhase;
import net.minecraft.entity.boss.dragon.phase.LandingPhase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LandingPhase.class)

public class MixinLandingPhase {
    @Redirect(method = "doServerTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getHeightmapPos(Lnet/minecraft/world/gen/Heightmap$Type;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/BlockPos;"))
    private BlockPos EnderDragonAlwaysLandsOnExitPortal(World instance, Heightmap.Type type, BlockPos blockPos){
        return blockPos.above(5); // 0 5 0
    }
}
