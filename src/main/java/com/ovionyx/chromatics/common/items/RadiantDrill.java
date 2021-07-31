package com.ovionyx.chromatics.common.items;

import com.simibubi.create.content.curiosities.armor.BackTankUtil;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.function.Consumer;

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
    public void inventoryTick(ItemStack item, World world, Entity player, int p_77663_4_, boolean selected) {
        if (!item.hasTag()) { item.setTag(new CompoundNBT()); }
        CompoundNBT nbt = item.getTag();
        super.inventoryTick(item, world, player, p_77663_4_, selected);
        if (nbt.getFloat("DrillSpeed") > 2 && !selected) {
            nbt.putFloat("DrillSpeed", nbt.getFloat("DrillSpeed") - 0.1f);
        }
        if (nbt.getFloat("DrillSpeed") < 2) {
            nbt.putFloat("DrillSpeed", 2);
        }
    }

    @Override
    public boolean mineBlock(ItemStack item, World world, BlockState block, BlockPos pos, LivingEntity player) {
        if (!item.hasTag()) { item.setTag(new CompoundNBT()); }
        CompoundNBT nbt = item.getTag();
        if (nbt.getFloat("DrillSpeed") < 20) {
            nbt.putFloat("DrillSpeed", nbt.getFloat("DrillSpeed") + 2f);
        }
        return super.mineBlock(item, world, block, pos, player);
    }

    @Override
    public float getDestroySpeed(ItemStack item, BlockState block) {
        if (!item.hasTag()) { item.setTag(new CompoundNBT()); }
        CompoundNBT nbt = item.getTag();
        if (item.isCorrectToolForDrops(block)) {
            return nbt.getFloat("DrillSpeed");
        } else {
            return 1;
        }
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
