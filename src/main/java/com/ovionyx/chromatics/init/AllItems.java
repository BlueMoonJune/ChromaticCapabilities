package com.ovionyx.chromatics.init;

import com.ovionyx.chromatics.Chromatics;
import com.ovionyx.chromatics.content.equipment.ToolMaterialList;
import com.ovionyx.chromatics.content.equipment.armour.ShadowArmorItem;
import com.ovionyx.chromatics.content.equipment.armour.ShadowChestplateItem;
import com.ovionyx.chromatics.content.equipment.armour.ShadowMaterial;
import com.ovionyx.chromatics.content.equipment.chainsaw.RadiantChainsawItem;
import com.ovionyx.chromatics.content.equipment.drill.RadiantDrillItem;
import com.ovionyx.chromatics.content.equipment.jackhammer.RadiantJackhammerItem;
import com.ovionyx.chromatics.content.equipment.knife.RadiantKnifeItem;
import com.ovionyx.chromatics.content.equipment.rapier.RadiantRapierItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class AllItems {
    private static final CreateRegistrate REGISTRATE = Chromatics.registrate()
            .creativeModeTab(() -> Chromatics.itemGroup);
    public static final ItemEntry<RadiantChainsawItem> CHAINSAW = REGISTRATE.item("radiant_chainsaw", p->new RadiantChainsawItem(ToolMaterialList.chainsaw, 0, 1.5f, p))
            .properties(p -> p.stacksTo(1)
                    .rarity(Rarity.RARE))
            .model(AssetLookup.itemModelWithPartials())
            .register();
    public static final ItemEntry<RadiantDrillItem> DRILL = REGISTRATE.item("radiant_drill", p->new RadiantDrillItem(ToolMaterialList.drill, 0, 1.5f, p))
            .properties(p -> p.stacksTo(1)
                    .rarity(Rarity.RARE))
            .model(AssetLookup.itemModelWithPartials())
            .register();
    public static final ItemEntry<RadiantJackhammerItem> JACKHAMMER = REGISTRATE.item("radiant_jackhammer", p->new RadiantJackhammerItem(ToolMaterialList.jackhammer, 0, 1.5f, p))
            .properties(p -> p.stacksTo(1)
                    .rarity(Rarity.RARE))
            .model(AssetLookup.itemModelWithPartials())
            .register();
    public static final ItemEntry<RadiantKnifeItem> KNIFE = REGISTRATE.item("radiant_knife", p->new RadiantKnifeItem(ToolMaterialList.rapier, 0, 1.5f, p))
            .properties(p -> p.stacksTo(1)
                    .rarity(Rarity.RARE))
            .model(AssetLookup.itemModelWithPartials())
            .register();
    public static final ItemEntry<RadiantRapierItem> RAPIER = REGISTRATE.item("radiant_rapier", p->new RadiantRapierItem(ToolMaterialList.rapier, 0, 1.5f, p))
            .properties(p -> p.stacksTo(1)
                    .rarity(Rarity.RARE))
            .model(AssetLookup.itemModelWithPartials())
            .register();
    public static final ItemEntry<ShadowArmorItem> SHADOW_STEEL_BOOTS = REGISTRATE.item("shadow_steel_boots", p ->new ShadowArmorItem(ShadowMaterial.SHADOWSTEEL, EquipmentSlot.FEET, p))
            .properties(p -> p.stacksTo(1)
                    .rarity(Rarity.RARE))
            .register();
    public static final ItemEntry<ShadowArmorItem> SHADOW_STEEL_LEGGINGS = REGISTRATE.item("shadow_steel_leggings", p ->new ShadowArmorItem(ShadowMaterial.SHADOWSTEEL, EquipmentSlot.LEGS, p))
            .properties(p -> p.stacksTo(1)
                    .rarity(Rarity.RARE))
            .register();
    public static final ItemEntry<ShadowChestplateItem> SHADOW_STEEL_CHESTPLATE = REGISTRATE.item("shadow_steel_chestplate", p ->new ShadowChestplateItem(ShadowMaterial.SHADOWSTEEL, EquipmentSlot.CHEST, p))
            .properties(p -> p.stacksTo(1)
                    .rarity(Rarity.RARE))
            .register();
    public static final ItemEntry<ShadowArmorItem> SHADOW_STEEL_HELMET = REGISTRATE.item("shadow_steel_helmet", p ->new ShadowArmorItem(ShadowMaterial.SHADOWSTEEL, EquipmentSlot.HEAD, p))
            .properties(p -> p.stacksTo(1)
                    .rarity(Rarity.RARE))
            .register();

    public static void register() {}
}
