package me.nukelord.bloodnwine;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.network.NetworkManager;
import net.minecraft.world.TrackedEntity;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

@Mod("bloodnwine2resources")
public class bloodnwine2resources
{
    public static final String MOD_ID = "bloodnwine2resources";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final HashMap<String,Long> map = new HashMap<>();

    public bloodnwine2resources() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Initializing Blood N Wine 2");
    }

/*
    @SubscribeEvent
    public void onPearlTP(final EntityTeleportEvent.EnderPearl event){
        LOGGER.warn("TPing");
    }
*/

/*    @SubscribeEvent
    public void onChat(final ClientChatEvent event) {
        LOGGER.warn(event.getMessage());

        if(event.getMessage().contains("print packet stats")){

            if(map.isEmpty()){

                //System.out.println("PACKET MAP IS EMPTY");
                return;
            }

            final StringBuilder stringBuilder = new StringBuilder("Packet Stats:");
            for(String key : map.keySet()){
                stringBuilder.append("\n      ").append(key).append(": ").append(map.get(key));
            }
            LOGGER.error(stringBuilder);
            //map.clear();
        }
    }*/
}
