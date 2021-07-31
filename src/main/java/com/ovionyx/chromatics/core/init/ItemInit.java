package com.ovionyx.chromatics.core.init;

import com.ovionyx.chromatics.Chromatics;

import com.ovionyx.chromatics.common.items.RadiantChainsaw;
import com.ovionyx.chromatics.common.items.RadiantDrill;
import com.ovionyx.chromatics.common.items.RadiantJackhammer;
import com.ovionyx.chromatics.common.items.SpecialItem;
import com.ovionyx.chromatics.lists.ToolMaterialList;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            Chromatics.MODID);

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<SpecialItem> TESTIER_ITEM = ITEMS.register("testier_item", () -> new SpecialItem(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<RadiantDrill> RADIANT_DRILL = ITEMS.register("radiant_drill", () -> new RadiantDrill(ToolMaterialList.drill, -1, 1.5f, new Item.Properties().addToolType(ToolType.SHOVEL,5).tab(ItemGroup.TAB_TOOLS).stacksTo(1)));
    public static final RegistryObject<RadiantJackhammer> RADIANT_JACKHAMMER = ITEMS.register("radiant_jackhammer", () -> new RadiantJackhammer(ToolMaterialList.jackhammer, 0, 1.5f, new Item.Properties().addToolType(ToolType.PICKAXE,5).tab(ItemGroup.TAB_TOOLS).stacksTo(1)));
    public static final RegistryObject<RadiantChainsaw> RADIANT_CHAINSAW = ITEMS.register("radiant_chainsaw", () -> new RadiantChainsaw(ToolMaterialList.chainsaw, 0, 1.5f, new Item.Properties().addToolType(ToolType.AXE,5).tab(ItemGroup.TAB_TOOLS).stacksTo(1)));


    //Block Items
    public static final RegistryObject<BlockItem> CHROMATIC_CASING = ITEMS.register("chromatic_casing", () -> new BlockItem(BlockInit.CHROMATIC_CASING.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

}
