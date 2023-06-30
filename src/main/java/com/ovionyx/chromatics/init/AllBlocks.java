package com.ovionyx.chromatics.init;

import com.ovionyx.chromatics.Chromatics;
import com.ovionyx.chromatics.foundation.util.ChromaticSpriteShifts;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MaterialColor;

public class AllBlocks {
    private static final CreateRegistrate REGISTRATE = Chromatics.registrate().creativeModeTab(
            () -> Chromatics.itemGroup
    );
    public static final BlockEntry<CasingBlock> CHROMATIC_CASING = REGISTRATE.block("chromatic_casing", CasingBlock::new)
            .initialProperties(() -> Blocks.COPPER_BLOCK)
            .transform(BuilderTransformers.casing(() -> ChromaticSpriteShifts.CHROMATIC_CASING))
            .simpleItem()
            .lang("Chromatic Casing")
            .register();

    public static final BlockEntry<CasingBlock> REFINED_RADIANCE_CASING =
            REGISTRATE.block("refined_radiance_casing", CasingBlock::new)
                    .properties(p -> p.color(MaterialColor.SNOW))
                    .transform(BuilderTransformers.casing(() -> AllSpriteShifts.REFINED_RADIANCE_CASING))
                    .properties(p -> p.lightLevel($ -> 12))
                    .lang("Radiant Casing")
                    .register();
    public static final BlockEntry<CasingBlock> SHADOW_STEEL_CASING =
            REGISTRATE.block("shadow_steel_casing", CasingBlock::new)
                    .properties(p -> p.color(MaterialColor.COLOR_BLACK))
                    .transform(BuilderTransformers.casing(() -> AllSpriteShifts.SHADOW_STEEL_CASING))
                    .lang("Shadow Casing")
                    .register();
    public static void register() {}
}
