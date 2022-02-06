package me.nukelord.bloodnwine.mixins;


import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.IPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SCustomPayloadPlayPacket;
import net.minecraft.network.play.server.SEntityPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.world.TrackedEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.nukelord.bloodnwine.bloodnwine2resources.map;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {


    //@Shadow private INetHandler packetListener;

/*    @Inject(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/IPacket;)V", at = @At("HEAD"))
    private void packetlogger(ChannelHandlerContext context, IPacket<?> packet, CallbackInfo ci) {

        final String name = packet.getClass().getName();
        map.put(name, map.getOrDefault(name, 0L) + 1);
        *//*if (packet instanceof SEntityPacket.RelativeMovePacket){

            System.out.println("RelativeMovePacket: "+((SEntityPacket.RelativeMovePacket) packet).entityId+","+((SEntityPacket.RelativeMovePacket) packet).xa+","+((SEntityPacket.RelativeMovePacket) packet).ya+","+((SEntityPacket.RelativeMovePacket) packet).za+","+((SEntityPacket.RelativeMovePacket) packet).onGround+","+((SEntityPacket.RelativeMovePacket) packet).hasPos+","+((SEntityPacket.RelativeMovePacket) packet).hasRot);
        }*//*
        if(packet instanceof SCustomPayloadPlayPacket){
            System.out.println("CustomPayloadPlay: "+((SCustomPayloadPlayPacket) packet).getIdentifier());
        }
        //System.out.println(name);
    }*/

    @Inject(method = "send(Lnet/minecraft/network/IPacket;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
    private void packetCanceller(IPacket<?> packet, GenericFutureListener<? extends Future<? super Void>> p_201058_2_, CallbackInfo ci) {
        if (packet instanceof SEntityPacket && isZero((SEntityPacket) packet))
            ci.cancel();
        else if (packet instanceof SEntityVelocityPacket && isZero((SEntityVelocityPacket) packet))
            ci.cancel();
    }

    private boolean isZero(SEntityPacket packet){
        return packet.hasPos && packet.onGround && !packet.hasRot && packet.xa==0 && packet.ya==0 && packet.za==0;
    }
    private boolean isZero(SEntityVelocityPacket packet){
        return packet.xa==0 && packet.ya==0 && packet.za==0;
    }
}
