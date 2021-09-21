package com.ovionyx.chromatics.common.items;

import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.Random;

public class RadiantKnife extends SwordItem {
    private static final MAX_USES = 2000; // Chnage this to the value you want or just make a new config file I guess
    
    public RadiantKnife(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        if (BackTankUtil.canAbsorbDamage(entity, MAX_USES / amount)) {
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
        CompoundNBT entData = entity.getPersistentData();
        CompoundNBT itemData = entity.getItem().getOrCreateTag();

        entity.setNoGravity(true);

        if (world.random.nextFloat() < 5) {
            basemotion = VecHelper.offsetRandomly(positionVec, world.random, 0.5F);
            world.addParticle(ParticleTypes.END_ROD, basemotion.x, positionVec.y, basemotion.z, 0.0D, -0.10000000149011612D, 0.0D);
        }

        if (stack.getDamageValue() > 0) {
            Random r = world.random;
            int range = 3;
            if (itemData.getInt("RepairCooldown") > 0) {
                itemData.putInt("RepairCooldown", itemData.getInt("RepairCooldown") - 1);
                return false;
            } else {
                itemData.putInt("RepairCooldown", 10);
                BlockPos randomOffset = new BlockPos(VecHelper.offsetRandomly(positionVec, r, (float) range));
                BlockState state = world.getBlockState(randomOffset);
                if (state.getLightValue(world, randomOffset) == 0) {
                    return false;
                } else if (state.getDestroySpeed(world, randomOffset) == -1.0F) {
                    return false;
                } else if (state.getBlock() == Blocks.BEACON) {
                    return false;
                } else {
                    RayTraceContext context = new RayTraceContext(positionVec, VecHelper.getCenterOf(randomOffset), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity);
                    if (!randomOffset.equals(world.clip(context).getBlockPos())) {
                        return false;
                    } else {
                        world.destroyBlock(randomOffset, false);
                        stack.setDamageValue(stack.getDamageValue() - 10);
                        if (stack.getDamageValue() <= 0) {

                            basemotion = new Vector3d(0.0D, 1.0D, 0.0D);

                            entity.setDeltaMovement(entity.getDeltaMovement().add(new Vector3d(0, 0.15, 0)));
                            basemotion = new Vector3d(0.0D, 1.0D, 0.0D);
                            world.addParticle(ParticleTypes.FLASH, positionVec.x, positionVec.y, positionVec.z, 0.0D, 0.0D, 0.0D);

                            for (int i = 0; i < 20; ++i) {
                                Vector3d motion = VecHelper.offsetRandomly(basemotion, world.random, 1.0F);
                                world.addParticle(ParticleTypes.WITCH, positionVec.x, positionVec.y, positionVec.z, motion.x, motion.y, motion.z);
                                world.addParticle(ParticleTypes.END_ROD, positionVec.x, positionVec.y, positionVec.z, motion.x, motion.y, motion.z);
                            }

                            if (stack.isEmpty()) {
                                entity.remove();
                            }

                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }
}
