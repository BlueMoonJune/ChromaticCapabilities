package com.ovionyx.chromatics.lists;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import com.simibubi.create.AllItems;

public enum ToolMaterialList implements IItemTier
{
    jackhammer(3.0f,12.0f,2571,5,17,AllItems.REFINED_RADIANCE.get()),
    drill(3.0f,3.0f,2571,5,17,AllItems.REFINED_RADIANCE.get());

    private float attackDamage, efficency;
    private int durability, harvestLevel, enchantmentValue;
    private Item repairMaterial;

    private ToolMaterialList(float attackDamage, float efficency, int durability, int harvestLevel, int enchantmentValue, Item repairMaterial) {
        this.attackDamage = attackDamage;
        this.efficency = efficency;
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
        return this.efficency;
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
