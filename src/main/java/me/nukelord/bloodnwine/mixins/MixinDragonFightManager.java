package me.nukelord.bloodnwine.mixins;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.end.DragonFightManager;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DragonFightManager.class)
public class MixinDragonFightManager {
    @Redirect(method = "spawnExitPortal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/server/ServerWorld;getHeightmapPos(Lnet/minecraft/world/gen/Heightmap$Type;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/BlockPos;"))
    private BlockPos exitportalbelow(ServerWorld instance, Heightmap.Type type, BlockPos blockPos){
        return blockPos.above(1); // 0 0 0
    }
}
