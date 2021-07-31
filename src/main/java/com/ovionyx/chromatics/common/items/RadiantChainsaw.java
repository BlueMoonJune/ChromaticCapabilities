package com.ovionyx.chromatics.common.items;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.curiosities.armor.BackTankUtil;
import com.simibubi.create.content.curiosities.armor.CopperArmorItem;
import com.simibubi.create.content.curiosities.armor.CopperBacktankItem;
import com.simibubi.create.foundation.utility.TreeCutter;
import com.simibubi.create.foundation.utility.VecHelper;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.function.Consumer;

import static java.lang.Math.max;

public class RadiantChainsaw extends AxeItem {
    private static boolean deforesting = false;

    public RadiantChainsaw(IItemTier p_i48530_1_, float p_i48530_2_, float p_i48530_3_, Properties p_i48530_4_) {
        super(p_i48530_1_, p_i48530_2_, p_i48530_3_, p_i48530_4_);
    }

    @Override
    public boolean mineBlock(ItemStack stack, World world, BlockState block, BlockPos pos, LivingEntity player) {
        Vector3d vec = player.getLookAngle();
        if (!deforesting) {
            deforesting = true;
            TreeCutter.findTree(world, pos).destroyBlocks(world, player, (dropPos, item) -> dropItemFromCutTree(world, pos, vec, dropPos, item));
            deforesting = false;
        }
        return super.mineBlock(stack, world, block, pos, player);
    }

    public static void dropItemFromCutTree(World world, BlockPos breakingPos, Vector3d fallDirection, BlockPos pos,
                                           ItemStack stack) {
        float distance = (float) Math.sqrt(pos.distSqr(breakingPos));
        Vector3d dropPos = VecHelper.getCenterOf(pos);
        ItemEntity entity = new ItemEntity(world, dropPos.x, dropPos.y, dropPos.z, stack);
        entity.setDeltaMovement(fallDirection.scale(distance / 20f));
        world.addFreshEntity(entity);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        ItemStack tank = BackTankUtil.get(entity);
        if (BackTankUtil.canAbsorbDamage(entity, amount)) {
            float air = BackTankUtil.getAir(tank);
            float maxAir = BackTankUtil.maxAir(tank);
            tank.getOrCreateTag().putFloat("Air",air+maxAir-amount);
            amount = 0;
        }
        return super.damageItem(stack, amount, entity, onBroken);
    }
}