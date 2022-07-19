package me.nukelord.bloodnwine.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.EndSpikeFeature;
import net.minecraft.world.gen.feature.EndSpikeFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EndSpikeFeature.class)
public class MixinEndSpikeFeature {
    @Inject(method = "placeSpike", at = @At("RETURN"), cancellable = true)
    private void extraEnderCrystal(IServerWorld worldserver, Random p_214553_2_, EndSpikeFeatureConfig p_214553_3_, EndSpikeFeature.EndSpike p_214553_4_, CallbackInfo ci) {
        EnderCrystalEntity endercrystalentity = EntityType.END_CRYSTAL.create(worldserver.getLevel());
        endercrystalentity.setBeamTarget(p_214553_3_.getCrystalBeamTarget());
        endercrystalentity.setInvulnerable(p_214553_3_.isCrystalInvulnerable());
        endercrystalentity.moveTo((double)p_214553_4_.getCenterX() + 0.5D, 30, (double)p_214553_4_.getCenterZ() + 0.5D, p_214553_2_.nextFloat() * 360.0F, 0.0F);
        worldserver.addFreshEntity(endercrystalentity);
    }
}
