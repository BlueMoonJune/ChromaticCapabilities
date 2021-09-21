package com.ovionyx.chromatics.common.items;

import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.LazyValue;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import com.ovionyx.chromatics.Chromatics;

@Mod.EventBusSubscriber(modid = Chromatics.MODID)
public class ShadowArmor extends ArmorItem {
    public ShadowArmor(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
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
    
    @SubscribeEvent
    public static void onLivingTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            PlayerEntity player = event.player;
            int pieces = 0;

            if (player.getPosY() <= 0) {
                Iterable<ItemStack> armorItems = player.getArmorInventoryList();

                for (ItemStack stack : armorItems) {
                    if (stack.getItem() instanceof ShadowArmor) {
                        if (!player.world.isRemote && player.getCooldownTracker().hasCooldown(stack.getItem()))
                            return;
                        pieces++;
                    }
                }
                if (pieces == 4) {
                    Vector3d playerVec = player.getPositionVec();
                    Vector3d particlePos = VecHelper.offsetRandomly(playerVec, player.world.getRandom(), 1.5F);
                    player.world.addParticle(ParticleTypes.END_ROD, particlePos.getX(), particlePos.getY(), particlePos.getZ(), 0, -0.10000000149011612D, 0);

                    if (!player.world.isRemote)
                        player.addPotionEffect(new EffectInstance(Effects.LEVITATION, 5, 100));
                        for (ItemStack stack : armorItems) {
                            stack.damageItem((int)(5 + player.world.getRandom().nextFloat() * 5), player, (entity) -> {
                                entity.sendBreakAnimation(stack.getEquipmentSlot());
                            });
                            player.getCooldownTracker().setCooldown(stack.getItem(), 60);
                        }

                }
            }
        }
    }
}
