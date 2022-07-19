package me.nukelord.bloodnwine.mixins;


import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import me.nukelord.bloodnwine.config.CommonConfig;
import net.minecraft.network.IPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SEntityPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static me.nukelord.bloodnwine.bloodnwine2resources.map;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {


    //@Shadow private INetHandler packetListener;

    @Inject(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/IPacket;)V", at = @At("HEAD"))
    private void packetlogger(ChannelHandlerContext context, IPacket<?> packet, CallbackInfo ci) {
        if(CommonConfig.packet_logger_enabled.get()) {
            final String name = packet.getClass().getName();
            map.put(name, map.getOrDefault(name, 0L) + 1);
        }
    }

    @Inject(method = "send(Lnet/minecraft/network/IPacket;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
    private void packetCanceller(IPacket<?> packet, GenericFutureListener<? extends Future<? super Void>> p_201058_2_, CallbackInfo ci) {
        if (isUselessPacket(packet))
            ci.cancel();
    }
    private boolean isUselessPacket(IPacket<?> possibleUselessPacket) {
        if (possibleUselessPacket instanceof SEntityPacket) {
            SEntityPacket packet = (SEntityPacket) possibleUselessPacket;
            if (possibleUselessPacket instanceof SEntityPacket.RelativeMovePacket) {
                return packet.xa == 0 && packet.ya == 0 && packet.za == 0;
            } else if (possibleUselessPacket instanceof SEntityPacket.MovePacket) {
                return packet.xa == 0 && packet.ya == 0 && packet.za == 0 && packet.xRot == 0 && packet.getyRot() == 0;
            } else if (possibleUselessPacket instanceof SEntityPacket.LookPacket) {
                return packet.xRot == 0 && packet.yRot == 0;
            }
        }
        return false;
    }
}
