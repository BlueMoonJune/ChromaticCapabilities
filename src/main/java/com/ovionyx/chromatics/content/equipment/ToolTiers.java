package com.ovionyx.chromatics.content.equipment;

import com.simibubi.create.AllItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class ToolTiers {
    public static final Tier rJackhammer = new Tier()
    {
        @Override
        public int getUses() {
            return 2571;
        }

        @Override
        public float getSpeed() {
            return 12.0f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 3.0f;
        }

        @Override
        public int getLevel() {
            return 5;
        }

        @Override
        public int getEnchantmentValue() {
            return 17;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(AllItems.REFINED_RADIANCE.get());
        }
    };
    public static final Tier rDrill = new Tier()
    {
        @Override
        public int getUses() {
            return 2571;
        }

        @Override
        public float getSpeed() {
            return 3.0f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 3.0f;
        }

        @Override
        public int getLevel() {
            return 5;
        }

        @Override
        public int getEnchantmentValue() {
            return 17;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(AllItems.REFINED_RADIANCE.get());
        }
    };
    public static final Tier rChainsaw = new Tier()
    {
        @Override
        public int getUses() {
            return 2671;
        }

        @Override
        public float getSpeed() {
            return 12.0f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 3.0f;
        }

        @Override
        public int getLevel() {
            return 5;
        }

        @Override
        public int getEnchantmentValue() {
            return 17;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(AllItems.REFINED_RADIANCE.get());
        }
    };
    public static final Tier rRapier = new Tier()
    {
        @Override
        public int getUses() {
            return 2671;
        }

        @Override
        public float getSpeed() {
            return 2f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 10.0f;
        }

        @Override
        public int getLevel() {
            return 5;
        }

        @Override
        public int getEnchantmentValue() {
            return 17;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(AllItems.REFINED_RADIANCE.get());
        }
    };
}
