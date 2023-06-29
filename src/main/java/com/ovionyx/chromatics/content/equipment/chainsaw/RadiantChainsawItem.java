package com.ovionyx.chromatics.content.equipment.chainsaw;

import com.ovionyx.chromatics.foundation.config.Common;
import com.ovionyx.chromatics.init.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.foundation.item.render.SimpleCustomRenderer;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RadiantChainsawItem extends AxeItem {
    private static boolean deforesting = false; // required as to not run into "recursions" over forge events on tree cutting
    public RadiantChainsawItem(Tier tier, int p_i48460_2_, float p_i48460_3_, Properties properties) {
        super(tier, p_i48460_2_, p_i48460_3_, properties);
    }

    // Moved away from Item#onBlockDestroyed as it does not get called in Creative
    public static void destroyTree(Level iWorld, BlockState state, BlockPos pos,
                                   Player player) {

        if (deforesting ||!(state.is(BlockTags.LOGS) || AllTags.AllBlockTags.SLIMY_LOGS.matches(state)) || player.isCrouching() || !(iWorld instanceof  Level))
            return;
        Level worldIn = (Level) iWorld;
        Vec3 vec = player.getLookAngle();

        deforesting = true;
        TreeCutter.findTree(worldIn, pos).destroyBlocks(worldIn, player, (dropPos, item) -> dropItemFromCutTree(worldIn, pos, vec, dropPos, item));
        deforesting = false;
    }
    @SubscribeEvent
    public static void onBlockDestroyed(BlockEvent.BreakEvent event) {
        ItemStack heldItemMainhand = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
        if (!AllItems.CHAINSAW.isIn(heldItemMainhand))
            return;
        destroyTree((Level) event.getWorld(), event.getState(), event.getPos(), event.getPlayer());
        findAndDamageChainsaw((Player) event.getPlayer());
    }


    public static void dropItemFromCutTree(Level world, BlockPos breakingPos, Vec3 fallDirection, BlockPos pos,
                                           ItemStack stack) {
        float distance = (float) Math.sqrt(pos.distSqr(breakingPos));
        Vec3 dropPos = VecHelper.getCenterOf(pos);
        ItemEntity entity = new ItemEntity(world, dropPos.x, dropPos.y, dropPos.z, stack);
        entity.setDeltaMovement(fallDirection.scale(distance / 20f));
        world.addFreshEntity(entity);
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(SimpleCustomRenderer.create(this, new RadiantChainsawRenderer()));
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity entity1) {
        stack.hurtAndBreak(1, entity1, (hurt) -> {
            findAndDamageChainsaw((Player) hurt);
        });
        return true;
    }
    private static void findAndDamageChainsaw(Player player) {
        if (player == null)
            return;
        if (player.level.isClientSide)
            return;
        InteractionHand hand = InteractionHand.MAIN_HAND;
        ItemStack chainsaw = player.getMainHandItem();
        if (!AllItems.CHAINSAW.isIn(chainsaw)) {
            chainsaw = player.getOffhandItem();
            hand = InteractionHand.OFF_HAND;
        }
        if (!AllItems.CHAINSAW.isIn(chainsaw))
            return;
        final InteractionHand h = hand;
        if (!BacktankUtil.canAbsorbDamage(player, maxUses()))
            chainsaw.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(h));
    }
    private static int maxUses() {
        return Common.RADIANT_TOOL_USES.get() * 2;
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
}
