package mod.bluemoonjune.chromatics;

import mod.bluemoonjune.chromatics.common.items.*;
import mod.bluemoonjune.chromatics.lists.ToolMaterialList;
import mod.bluemoonjune.chromatics.common.items.models.*;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import mod.bluemoonjune.chromatics.common.items.*;
import mod.bluemoonjune.chromatics.common.items.models.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.Tags;

public class AllItems {

    private static final CreateRegistrate REGISTRATE = Chromatics.registrate().creativeModeTab(() -> Chromatics.BASE_CREATIVE_TAB);

    static {
        REGISTRATE.startSection(AllSections.MATERIALS);
    }

    public static void register() {
    }

    public static final ItemEntry<Item> TEST_ITEM = REGISTRATE.item("test_item", Item::new).properties(p -> p.rarity(Rarity.EPIC)).register();
    public static final ItemEntry<SpecialItem> TESTIER_ITEM = REGISTRATE.item("testier_item", SpecialItem::new).properties(p -> p.rarity(Rarity.EPIC)).register();

    public static final ItemEntry<RadiantDrill> RADIANT_DRILL = REGISTRATE.item("radiant_drill", p->new RadiantDrill(ToolMaterialList.drill, 0f, 1.5f, p)).properties(p -> p.rarity(Rarity.RARE).addTags(Tags.SHOVEL,5)).transform(CreateRegistrate.customRenderedItem(() -> RadiantDrillModel::new)).register();
    public static final ItemEntry<RadiantChainsaw> RADIANT_CHAINSAW = REGISTRATE.item("radiant_chainsaw", p->new RadiantChainsaw(ToolMaterialList.chainsaw, 0f, 1.5f, p)).properties(p -> p.rarity(Rarity.RARE).addTags(Tags.AXE,5)).transform(CreateRegistrate.customRenderedItem(() -> RadiantChainsawModel::new)).register();
    public static final ItemEntry<RadiantJackhammer> RADIANT_JACKHAMMER = REGISTRATE.item("radiant_jackhammer", p->new RadiantJackhammer(ToolMaterialList.jackhammer, 0, 1.5f, p)).properties(p -> p.rarity(Rarity.RARE).addTags(Tags.PICKAXE,5)).transform(CreateRegistrate.customRenderedItem(() -> RadiantJackhammerModel::new)).register();
    public static final ItemEntry<RadiantRapier> RADIANT_RAPIER = REGISTRATE.item("radiant_rapier", p->new RadiantRapier(ToolMaterialList.rapier, 0, 1.5f, p)).properties(p -> p.rarity(Rarity.RARE)).transform(CreateRegistrate.customRenderedItem(() -> RadiantRapierModel::new)).register();
    public static final ItemEntry<RadiantKnife> RADIANT_Knife = REGISTRATE.item("radiant_knife", p->new RadiantKnife(ToolMaterialList.rapier, 0, 1.5f, p)).properties(p -> p.rarity(Rarity.RARE)).transform(CreateRegistrate.customRenderedItem(() -> RadiantKnifeModel::new)).register();

    public static final ItemEntry<ShadowArmor> SHADOW_STEEL_BOOTS = REGISTRATE.item("shadow_steel_boots", p ->new ShadowArmor(ShadowArmorMaterial.SHADOWSTEEL, EquipmentSlotType.FEET, new Item.Properties().rarity(Rarity.RARE))).register();
    public static final ItemEntry<ShadowArmor> SHADOW_STEEL_LEGGINGS = REGISTRATE.item("shadow_steel_leggings", p ->new ShadowArmor(ShadowArmorMaterial.SHADOWSTEEL, EquipmentSlotType.LEGS, new Item.Properties().rarity(Rarity.RARE))).register();
    public static final ItemEntry<ShadowChestplate> SHADOW_STEEL_CHESTPLATE = REGISTRATE.item("shadow_steel_chestplate", p ->new ShadowChestplate(ShadowArmorMaterial.SHADOWSTEEL, EquipmentSlotType.CHEST, new Item.Properties().rarity(Rarity.RARE))).register();
    public static final ItemEntry<ShadowArmor> SHADOW_STEEL_HELMET = REGISTRATE.item("shadow_steel_helmet", p ->new ShadowArmor(ShadowArmorMaterial.SHADOWSTEEL, EquipmentSlotType.HEAD, new Item.Properties().rarity(Rarity.RARE))).register();
}
