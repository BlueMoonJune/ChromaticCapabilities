package com.ovionyx.chromatics.content.equipment;

import com.simibubi.create.AllItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum ToolMaterialList implements Tier
{
    jackhammer(3.0f,12.0f,2571,5,17,AllItems.REFINED_RADIANCE.get()),
    drill(3.0f,3.0f,2571,5,17,AllItems.REFINED_RADIANCE.get()),
    chainsaw(3.0f,12f,2671,5,17,AllItems.REFINED_RADIANCE.get()),
    rapier(10.0f,2f,2671,5,17,AllItems.REFINED_RADIANCE.get());

    private float attackDamage, efficiency;
    private int durability, harvestLevel, enchantmentValue;
    private Item repairMaterial;

    private ToolMaterialList(float attackDamage, float efficiency, int durability, int harvestLevel, int enchantmentValue, Item repairMaterial) {
        this.attackDamage = attackDamage;
        this.efficiency = efficiency;
        this.durability = durability;
        this.harvestLevel = harvestLevel;
        this.enchantmentValue = enchantmentValue;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getUses() {
        return this.durability;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(this.repairMaterial);
    }
}
