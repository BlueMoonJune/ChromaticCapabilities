package com.ovionyx.chromatics.content.equipment.rapier;

import com.ovionyx.chromatics.content.equipment.ToolTiers;
import com.ovionyx.chromatics.foundation.config.Common;
import com.ovionyx.chromatics.init.AllItems;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.foundation.item.render.SimpleCustomRenderer;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.lang.Math.*;

public class RadiantRapierItem extends AxeItem {

    public RadiantRapierItem(Properties properties) {
        super(ToolTiers.rRapier, 0, 1.5f, properties);
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity player, LivingEntity enemy) {
        Predicate alwaysTrue = i -> (true);
        Vec2 rot = player.getRotationVector();
        float yaw = rot.x;
        float pitch = rot.y;
        Vec3 look = new Vec3(
                (sin(toRadians(yaw))*cos(toRadians(pitch))),
                (cos(toRadians(yaw))*cos(toRadians(pitch))),
                sin(toRadians(pitch)));
        Level world = player.level;
        List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, new AABB(new BlockPos(player.getEyePosition(0)), new BlockPos(player.getEyePosition(0).add(look.scale(5)))), alwaysTrue);
        for (LivingEntity ent : entities) {
            return super.hurtEnemy(stack, player, ent);
        }
        findAndDamageRapier((Player) player);
        return super.hurtEnemy(stack, player, enemy);
    }
    private static void findAndDamageRapier(Player player) {
        if (player == null)
            return;
        if (player.level.isClientSide)
            return;
        InteractionHand hand = InteractionHand.MAIN_HAND;
        ItemStack rapier = player.getMainHandItem();
        if (!AllItems.RAPIER.isIn(rapier)) {
            rapier = player.getOffhandItem();
            hand = InteractionHand.OFF_HAND;
        }
        if (!AllItems.RAPIER.isIn(rapier))
            return;
        final InteractionHand h = hand;
        if (!BacktankUtil.canAbsorbDamage(player, maxUses()))
            rapier.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(h));
    }
    private static int maxUses() {
        int defaultMaxUses = Common.RADIANT_TOOL_USES.get();
        double maxUses = (double) defaultMaxUses /2;
        return (int) maxUses;
    }
    @Override
    public boolean isBarVisible(ItemStack stack) {
        return BacktankUtil.isBarVisible(stack, maxUses());
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return BacktankUtil.getBarWidth(stack, maxUses());
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return BacktankUtil.getBarColor(stack, maxUses());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(SimpleCustomRenderer.create(this, new RadiantRapierRenderer()));
    }
    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {

        Vec3 basemotion;

        entity.lifespan = 6000;

        Level world = entity.level;
        Vec3 positionVec = entity.position();
        CompoundTag entData = entity.getPersistentData();
        CompoundTag itemData = entity.getItem().getOrCreateTag();

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
                if (state.getLightEmission(world, randomOffset) == 0) {
                    return false;
                } else if (state.getDestroySpeed(world, randomOffset) == -1.0F) {
                    return false;
                } else if (state.getBlock() == Blocks.BEACON) {
                    return false;
                } else {
                    ClipContext context = new ClipContext(positionVec, VecHelper.getCenterOf(randomOffset), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity);
                    if (!randomOffset.equals(world.clip(context).getBlockPos())) {
                        return false;
                    } else {
                        world.destroyBlock(randomOffset, false);
                        stack.setDamageValue(stack.getDamageValue() - 10);
                        if (stack.getDamageValue() <= 0) {

                            basemotion = new Vec3(0.0D, 1.0D, 0.0D);

                            entity.setDeltaMovement(entity.getDeltaMovement().add(new Vec3(0, 0.15, 0)));
                            basemotion = new Vec3(0.0D, 1.0D, 0.0D);
                            world.addParticle(ParticleTypes.FLASH, positionVec.x, positionVec.y, positionVec.z, 0.0D, 0.0D, 0.0D);

                            for (int i = 0; i < 20; ++i) {
                                Vec3 motion = VecHelper.offsetRandomly(basemotion, world.random, 1.0F);
                                world.addParticle(ParticleTypes.WITCH, positionVec.x, positionVec.y, positionVec.z, motion.x, motion.y, motion.z);
                                world.addParticle(ParticleTypes.END_ROD, positionVec.x, positionVec.y, positionVec.z, motion.x, motion.y, motion.z);
                            }

                            if (stack.isEmpty()) {
                                entity.remove(Entity.RemovalReason.DISCARDED);
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
