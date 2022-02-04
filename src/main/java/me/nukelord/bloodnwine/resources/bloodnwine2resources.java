package me.nukelord.bloodnwine.resources;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("bloodnwine2resources")
public class bloodnwine2resources
{
    public static final String MOD_ID = "bloodnwine2resources";
    private static final Logger LOGGER = LogManager.getLogger();

    public bloodnwine2resources() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Initializing Blood N Wine 2");
    }
}
