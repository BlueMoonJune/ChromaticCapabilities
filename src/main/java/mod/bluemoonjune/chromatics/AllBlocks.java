package mod.bluemoonjune.chromatics;

import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;

import java.util.function.Supplier;

public class AllBlocks {
    private static final CreateRegistrate REGISTRATE = Chromatics.registrate().creativeModeTab(() -> Chromatics.BASE_CREATIVE_TAB);

    public static void register() {
    }

    public static final BlockEntry<CasingBlock> CHROMATIC_CASING = ((BlockBuilder)REGISTRATE.block("chromatic_casing", CasingBlock::new).transform(BuilderTransformers.casing((Supplier<CTSpriteShiftEntry>) AllSpriteShifts.CHROMATIC_CASING))).register();

}


