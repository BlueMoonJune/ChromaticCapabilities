package com.ovionyx.chromatics.common.items;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.KeybindTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.Iterator;

public class RadiantDrill extends ShovelItem {

    float digSpeed;

    public RadiantDrill(IItemTier ToolMaterial, float attackOffset, float attackSpeed, Properties properties) {
        super(ToolMaterial, attackOffset, attackSpeed, properties);
    }

    @java.lang.Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, java.util.List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("tooltip.chromatics.shift_info"));
        if(InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
            tooltip.add(new TranslationTextComponent("tooltip.chromatics.radiant_drill"));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void inventoryTick(ItemStack item, World world, Entity player, int slot, boolean selected) {
        if (!item.hasTag()) { item.setTag(new CompoundNBT()); }
        CompoundNBT nbt = item.getTag();
        if (selected && Minecraft.getInstance().options.keyAttack.isDown()) {

            if (nbt.getFloat("DrillSpeed") < 20) {
                nbt.putFloat("DrillSpeed", nbt.getFloat("DrillSpeed") + 0.1f);
            }
        } else if(nbt.getFloat("DrillSpeed") > 0) {
            nbt.putFloat("DrillSpeed", nbt.getFloat("DrillSpeed") - 0.5f);
        }
        super.inventoryTick(item, world, player, slot, selected);
    }

    @Override
    public float getDestroySpeed(ItemStack item, BlockState p_150893_2_) {
        if (!item.hasTag()) { item.setTag(new CompoundNBT()); }
        CompoundNBT nbt = item.getTag();
        return nbt.getFloat("DrillSpeed");
    }
}
