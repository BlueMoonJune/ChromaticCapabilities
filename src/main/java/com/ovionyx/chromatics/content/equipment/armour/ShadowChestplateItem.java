package com.ovionyx.chromatics.content.equipment.armour;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ShadowChestplateItem extends ShadowArmorItem {
    public ShadowChestplateItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
    }
    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (entity.getY() < 0 && entity instanceof LivingEntity) {
            LivingEntity player = (LivingEntity)entity;
            Iterable<ItemStack> armorItems = entity.getArmorSlots();
            int pieces = 0;
            for (ItemStack item : armorItems) {
                if (item.getItem().getClass() == ShadowArmorItem.class) {
                    pieces++;
                }
            }
            if (pieces == 4) {
                player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 5, 100));
            }
        }
        super.inventoryTick(stack, world, entity, p_77663_4_, p_77663_5_);
    }
}
