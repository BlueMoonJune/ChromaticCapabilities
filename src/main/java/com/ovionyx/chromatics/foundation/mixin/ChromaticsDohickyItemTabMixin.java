package com.ovionyx.chromatics.foundation.mixin;

import com.ovionyx.chromatics.Chromatics;
import com.simibubi.create.content.legacy.NoGravMagicalDohickyItem;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(NoGravMagicalDohickyItem.class)
public class ChromaticsDohickyItemTabMixin {

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == Chromatics.itemGroup) {
            pItems.add(new ItemStack((NoGravMagicalDohickyItem)(Object)this));
        }
    }
}
