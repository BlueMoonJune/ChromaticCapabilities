package com.ovionyx.chromatics.content.equipment.armour;

import com.ovionyx.chromatics.Chromatics;
import com.simibubi.create.AllItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.util.LazyLoadedValue;

import java.util.function.Supplier;

public enum ShadowMaterial implements ArmorMaterial {

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
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ShadowMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlot p_200896_1_) {
        return 0;
    }

    public int getDefenseForSlot(EquipmentSlot p_200902_1_) {
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
