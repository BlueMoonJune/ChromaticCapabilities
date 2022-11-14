package mod.bluemoonjune.chromatics.common.items;

import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

import static java.lang.Math.*;

public class RadiantRapier extends AxeItem {

    public RadiantRapier(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    @java.lang.Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, java.util.List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("tooltip.chromatics.shift_info"));
        if(InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
            tooltip.add(new TranslationTextComponent("tooltip.chromatics.radiant_rapier"));
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity player, LivingEntity enemy) {
        Predicate alwaysTrue = i -> (true);
        Vector2f rot = player.getRotationVector();
        float yaw = rot.x;
        float pitch = rot.y;
        Vector3d look = new Vector3d(
                (sin(toRadians(yaw))*cos(toRadians(pitch))),
                (cos(toRadians(yaw))*cos(toRadians(pitch))),
                sin(toRadians(pitch)));
        World world = player.level;
        List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(new BlockPos(player.getEyePosition(0)), new BlockPos(player.getEyePosition(0).add(look.scale(5)))), alwaysTrue);
        for (LivingEntity ent : entities) {
            return super.hurtEnemy(stack, player, ent);
        }
        return super.hurtEnemy(stack, player, enemy);
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
