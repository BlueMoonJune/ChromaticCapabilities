package com.ovionyx.chromatics.common.items;

import com.simibubi.create.content.curiosities.armor.BackTankUtil;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;
import java.util.Random;
import java.util.function.Consumer;

import javax.annotation.Nullable;

public class RadiantJackhammer extends PickaxeItem {
    private static final MAX_USES = 2000; // Chnage this to the value you want or just make a new config file I guess

    public RadiantJackhammer(IItemTier p_i48478_1_, int p_i48478_2_, float p_i48478_3_, Properties p_i48478_4_) {
        super(p_i48478_1_, p_i48478_2_, p_i48478_3_, p_i48478_4_);
    }
    
    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        if (BackTankUtil.canAbsorbDamage(entity, MAX_USES / amount)) {
            amount = 0;
        }
        return super.damageItem(stack, amount, entity, onBroken);
    }

    BlockPos MineRandomBlock(World world, Entity player, BlockPos pos) {
        Random rand = new Random();
        int x = rand.nextInt(3)-1;
        int y = rand.nextInt(3)-1;
        int z = rand.nextInt(3)-1;
        BlockPos newpos = new BlockPos(pos.getX() + x,pos.getY() + y,pos.getZ() + z);

        if (world.getBlockState(newpos).getDestroySpeed(world, newpos) > -1) {
            world.destroyBlock(newpos, true, player, 5);
        }
        return newpos;
    }

    @java.lang.Override
    public boolean mineBlock(ItemStack stack, World world, BlockState block, BlockPos pos, LivingEntity entity) {
        for (int i = 0; i < 20; i++) {
            MineRandomBlock(world, entity, MineRandomBlock(world, entity, MineRandomBlock(world, entity, MineRandomBlock(world, entity, pos))));
        }
        return super.mineBlock(stack, world, block, pos, entity);
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

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {

        Vector3d basemotion;

        entity.lifespan = 6000;

        World world = entity.level;
        Vector3d positionVec = entity.position();

        entity.setNoGravity(true);

        if (world.random.nextFloat() < 5) {
            basemotion = VecHelper.offsetRandomly(positionVec, world.random, 0.5F);
            world.addParticle(ParticleTypes.END_ROD, basemotion.x, positionVec.y, basemotion.z, 0.0D, -0.10000000149011612D, 0.0D);
        }
        return false;
    }
}
