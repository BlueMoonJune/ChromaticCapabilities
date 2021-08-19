package com.ovionyx.chromatics;

import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.builders.BlockBuilder;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;

public class AllBlocks {
    private static final CreateRegistrate REGISTRATE = Chromatics.registrate().itemGroup(() -> Chromatics.BASE_CREATIVE_TAB);

    public static void register() {
    }

    public static final BlockEntry<CasingBlock> CHROMATIC_CASING = ((BlockBuilder)REGISTRATE.block("chromatic_casing", CasingBlock::new).transform(BuilderTransformers.casing(AllSpriteShifts.CHROMATIC_CASING))).register();

}


