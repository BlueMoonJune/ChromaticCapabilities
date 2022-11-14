package mod.bluemoonjune.chromatics;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.data.CreateRegistrate;

public class AllSpriteShifts {
    private static final CreateRegistrate REGISTRATE = (CreateRegistrate) Create.registrate().itemGroup(() -> Create.BASE_CREATIVE_TAB);

    public static void register() {
    }

    static CTSpriteShiftEntry omni(String name) {
        return CTSpriteShifter.getCT(CTSpriteShifter.CTType.OMNIDIRECTIONAL, name);
    }

    public static final CTSpriteShiftEntry CHROMATIC_CASING = omni("chromatic_casing");

}
