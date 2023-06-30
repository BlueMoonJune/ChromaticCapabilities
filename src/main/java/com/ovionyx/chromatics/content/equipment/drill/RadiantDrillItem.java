package com.ovionyx.chromatics.content.equipment.drill;

import com.ovionyx.chromatics.content.equipment.ToolTiers;
import com.ovionyx.chromatics.foundation.config.Common;
import com.ovionyx.chromatics.init.AllItems;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.foundation.item.render.SimpleCustomRenderer;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RadiantDrillItem extends ShovelItem {
    float digSpeed;

    public RadiantDrillItem(Properties properties) {
        super(ToolTiers.rDrill, 0, 1.5f, properties);
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(SimpleCustomRenderer.create(this, new RadiantDrillRenderer()));
    }
    @Override
    public void inventoryTick(ItemStack item, Level world, Entity player, int p_77663_4_, boolean selected) {
        if (!item.hasTag()) { item.setTag(new CompoundTag()); }
        CompoundTag nbt = item.getTag();
        super.inventoryTick(item, world, player, p_77663_4_, selected);
        if (nbt.getFloat("DrillSpeed") > 2 && !selected) {
            nbt.putFloat("DrillSpeed", nbt.getFloat("DrillSpeed") - 0.1f);
        }
        if (nbt.getFloat("DrillSpeed") < 2) {
            nbt.putFloat("DrillSpeed", 2);
        }
    }
    @Override
    public boolean mineBlock(ItemStack item, Level world, BlockState block, BlockPos pos, LivingEntity player) {
        if (!item.hasTag()) { item.setTag(new CompoundTag()); }
        CompoundTag nbt = item.getTag();
        if (nbt.getFloat("DrillSpeed") < 20) {
            nbt.putFloat("DrillSpeed", nbt.getFloat("DrillSpeed") + 2f);
        }
        findAndDamageDrill((Player) player);
        return super.mineBlock(item, world, block, pos, player);
    }

    @Override
    public float getDestroySpeed(ItemStack item, BlockState block) {
        if (!item.hasTag()) { item.setTag(new CompoundTag()); }
        CompoundTag nbt = item.getTag();
        if (item.isCorrectToolForDrops(block)) {
            return nbt.getFloat("DrillSpeed");
        } else {
            return 1;
        }
    }
    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {

        Vec3 basemotion;

        entity.lifespan = 6000;

        Level world = entity.level;
        Vec3 positionVec = entity.position();

        entity.setNoGravity(true);

        if (world.random.nextFloat() < 5) {
            basemotion = VecHelper.offsetRandomly(positionVec, world.random, 0.5F);
            world.addParticle(ParticleTypes.END_ROD, basemotion.x, positionVec.y, basemotion.z, 0.0D, -0.10000000149011612D, 0.0D);
        }
        return false;
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity entity1) {
        stack.hurtAndBreak(1, entity1, (hurt) -> {
            findAndDamageDrill((Player) hurt);
        });
        return true;
    }
    private static void findAndDamageDrill(Player player) {
        if (player == null)
            return;
        if (player.level.isClientSide)
            return;
        InteractionHand hand = InteractionHand.MAIN_HAND;
        ItemStack drill = player.getMainHandItem();
        if (!AllItems.DRILL.isIn(drill)) {
            drill = player.getOffhandItem();
            hand = InteractionHand.OFF_HAND;
        }
        if (!AllItems.DRILL.isIn(drill))
            return;
        final InteractionHand h = hand;
        if (!BacktankUtil.canAbsorbDamage(player, maxUses()))
            drill.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(h));
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
}
