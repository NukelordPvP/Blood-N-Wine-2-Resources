package me.nukelord.bloodnwine.mixins;



import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnderPearlEntity.class)
public class MixinEnderPearlEntity {

    @Redirect(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/item/EnderPearlEntity;getOwner()Lnet/minecraft/entity/Entity;"))
    private Entity findPlayer(EnderPearlEntity instance) {
        if (instance.ownerUUID != null && instance.level instanceof ServerWorld) {
            Entity entity=null;
            for(ServerWorld level : instance.level.getServer().getAllLevels()){
                entity= level.getEntity(instance.ownerUUID);
                if(entity!=null)
                    break;
            }
            return entity;
        } else {
            return instance.ownerNetworkId != 0 ? instance.level.getEntity(instance.ownerNetworkId) : null;
        }
    }

    @Redirect(method = "onHit", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/ServerPlayerEntity;level:Lnet/minecraft/world/World;", opcode = 180)) //180 = Opcodes.GETFIELD
    private World ender_pearl_multi_dimensional_teleportation(ServerPlayerEntity instance) {
        // makes player's dimension equal to pearl in the dimension check.. allows enderpearl 8x TP if you throw them inside a portal
//        System.out.println("pearl: "+((EnderPearlEntity)(Object)this).level.dimension());
//        System.out.println("player: "+instance.level.dimension());
        return ((EnderPearlEntity)(Object)this).level;
    }

    @Redirect(method = "changeDimension", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/item/EnderPearlEntity;getOwner()Lnet/minecraft/entity/Entity;"))
    private Entity changeDimension(EnderPearlEntity instance) {
        return null; // set to null to bypass ender pearl removal
    }



}
