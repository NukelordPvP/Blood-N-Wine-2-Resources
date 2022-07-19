package me.nukelord.bloodnwine.config;

import net.minecraftforge.common.ForgeConfigSpec;


public class CommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<Boolean> packet_logger_enabled;

    static {
        BUILDER.push("Packet Logger Settings (for debugging purposes)");
        packet_logger_enabled = BUILDER.comment("Enable or disable packet logger").define("enable-packet-logger", false);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
