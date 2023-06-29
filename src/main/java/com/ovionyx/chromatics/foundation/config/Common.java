package com.ovionyx.chromatics.foundation.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Common {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> RADIANT_TOOL_USES;

    static {
        BUILDER.push("Common Configs for Chromatics");
        RADIANT_TOOL_USES = BUILDER.comment("How many uses a Radiant Tool has before it fully depletes a BackTank.")
                .define("Radiant Tool Uses", 1024);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
