package com.ovionyx.chromatics.events;

import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.components.flywheel.engine.EngineBlock;
import com.simibubi.create.foundation.config.AllConfigs;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.TooltipHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.ovionyx.chromatics.Chromatics;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Chromatics.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {

    private static final String itemPrefix = "item." + Chromatics.MOD_ID;
    private static final String blockPrefix = "block." + Chromatics.MOD_ID;

    /*
    Stole from Create since the tooltip system was hardcoded and only works when your mod ID starts with "Create",
    for example, "createautomated", "createadditions", etc.
    */
    @SubscribeEvent
    public static void addToItemTooltip(ItemTooltipEvent event) {
        if (!AllConfigs.CLIENT.tooltips.get())
            return;
        if (event.getPlayer() == null)
            return;

        ItemStack stack = event.getItemStack();
        String translationKey = stack.getItem().getTranslationKey(stack);

        if (translationKey.startsWith(itemPrefix) || translationKey.startsWith(blockPrefix))
            if (TooltipHelper.hasTooltip(stack, event.getPlayer())) {
                List<ITextComponent> itemTooltip = event.getToolTip();
                List<ITextComponent> toolTip = new ArrayList<>();
                toolTip.add(itemTooltip.remove(0));
                TooltipHelper.getTooltip(stack)
                        .addInformation(toolTip);
                itemTooltip.addAll(0, toolTip);
            }

        if (stack.getItem() instanceof BlockItem) {
            BlockItem item = (BlockItem) stack.getItem();
            if (item.getBlock() instanceof IRotate || item.getBlock() instanceof EngineBlock) {
                List<ITextComponent> kineticStats = ItemDescription.getKineticStats(item.getBlock());
                if (!kineticStats.isEmpty()) {
                    event.getToolTip()
                            .add(new StringTextComponent(""));
                    event.getToolTip()
                            .addAll(kineticStats);
                }
            }
        }
    }
}
