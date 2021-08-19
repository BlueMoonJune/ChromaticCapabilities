package com.ovionyx.chromatics.common.items;

import com.ovionyx.chromatics.Chromatics;
import com.simibubi.create.AllItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ShadowArmorMaterial implements IArmorMaterial {
    SHADOWSTEEL("shadow_steel", 35, new int[]{3,6,8,3}, 20, null, 2.5f, 0, () -> {
        return Ingredient.of(AllItems.SHADOW_STEEL.asStack());
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    private ShadowArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
        return 0;
    }

    public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
        return 0;
    }

    public int getEnchantmentValue() {
        return 0;
    }

    public SoundEvent getEquipSound() {
        return null;
    }

    public Ingredient getRepairIngredient() {
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return Chromatics.MODID + ":" + this.name;
    }

    public float getToughness() {
        return 0;
    }

    public float getKnockbackResistance() {
        return 0;
    }
}
