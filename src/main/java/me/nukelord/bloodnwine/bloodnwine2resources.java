package me.nukelord.bloodnwine;

import me.nukelord.bloodnwine.config.CommonConfig;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
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
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, "bnw2resources.toml");
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Initializing Blood N Wine 2 Resources mod");
    }

    @SubscribeEvent
    public void onChat(final ClientChatEvent event) {
        if(CommonConfig.packet_logger_enabled.get()) {

            if (event.getMessage().contains("/packetstats")) {

                if (map.isEmpty()) {
                    //System.out.println("PACKET MAP IS EMPTY");
                    return;
                }

                final StringBuilder stringBuilder = new StringBuilder("Packet Stats:");
                for (String key : map.keySet()) {
                    stringBuilder.append("\n      ").append(key.substring(22)).append(": ").append(map.get(key));
                }
                LOGGER.info(stringBuilder);
            }
        }
    }

}
