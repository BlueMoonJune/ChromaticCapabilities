package com.ovionyx.chromatics.init;

import com.ovionyx.chromatics.Chromatics;
import com.ovionyx.chromatics.foundation.util.ChromaticSpriteShifts;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Blocks;

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

    public static void register() {}
}
