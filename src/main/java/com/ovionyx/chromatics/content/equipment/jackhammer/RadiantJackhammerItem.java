package com.ovionyx.chromatics.content.equipment.jackhammer;

import com.ovionyx.chromatics.foundation.config.Common;
import com.ovionyx.chromatics.init.AllItems;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.foundation.item.render.SimpleCustomRenderer;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.Consumer;
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RadiantJackhammerItem extends PickaxeItem {

    public RadiantJackhammerItem(Tier tier, int p_i48460_2_, float p_i48460_3_, Properties properties) {
        super(tier, p_i48460_2_, p_i48460_3_, properties);
    }
    BlockPos MineRandomBlock(Level world, Entity player, BlockPos pos) {
        Random rand = new Random();
        int x = rand.nextInt(3)-1;
        int y = rand.nextInt(3)-1;
        int z = rand.nextInt(3)-1;
        BlockPos newpos = new BlockPos(pos.getX() + x,pos.getY() + y,pos.getZ() + z);

        if (world.getBlockState(newpos).getDestroySpeed(world, newpos) > -1) {
            world.destroyBlock(newpos, true, player, 5);
            findAndDamageJackhammer((Player) player);
        }
        return newpos;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState block, BlockPos pos, LivingEntity entity) {
        for (int i = 0; i < 20; i++) {
            MineRandomBlock(world, entity, MineRandomBlock(world, entity, MineRandomBlock(world, entity, MineRandomBlock(world, entity, pos))));
        }
        return super.mineBlock(stack, world, block, pos, entity);
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity entity1) {
        stack.hurtAndBreak(1, entity1, (hurt) -> {
            findAndDamageJackhammer((Player) hurt);
        });
        return true;
    }
    private static void findAndDamageJackhammer(Player player) {
        if (player == null)
            return;
        if (player.level.isClientSide)
            return;
        InteractionHand hand = InteractionHand.MAIN_HAND;
        ItemStack jackhammer = player.getMainHandItem();
        if (!AllItems.JACKHAMMER.isIn(jackhammer)) {
            jackhammer = player.getOffhandItem();
            hand = InteractionHand.OFF_HAND;
        }
        if (!AllItems.JACKHAMMER.isIn(jackhammer))
            return;
        final InteractionHand h = hand;
        if (!BacktankUtil.canAbsorbDamage(player, maxUses()))
            jackhammer.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(h));
    }
    private static int maxUses() {
        return Common.RADIANT_TOOL_USES.get();

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
    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(SimpleCustomRenderer.create(this, new RadiantJackhammerRenderer()));
    }
}
