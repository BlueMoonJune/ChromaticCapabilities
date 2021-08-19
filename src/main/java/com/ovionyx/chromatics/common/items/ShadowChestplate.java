package com.ovionyx.chromatics.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ShadowChestplate extends ShadowArmor {

    public ShadowChestplate(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (entity.getY() < 0 && entity instanceof LivingEntity) {
            LivingEntity player = (LivingEntity)entity;
            Iterable<ItemStack> armorItems = entity.getArmorSlots();
            int pieces = 0;
            for (ItemStack item : armorItems) {
                if (item.getItem().getClass() == ShadowArmor.class) {
                    pieces++;
                }
            }
            if (pieces == 4) {
                player.addEffect(new EffectInstance(Effects.LEVITATION, 5, 100));
            }
        }
        super.inventoryTick(stack, world, entity, p_77663_4_, p_77663_5_);
    }


}
