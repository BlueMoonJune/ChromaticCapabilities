package com.ovionyx.chromatics.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;

public class SpecialItem extends Item {

    public SpecialItem(Properties properties) {
        super(properties);
    }

    @java.lang.Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, java.util.List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if(InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
            tooltip.add(new StringTextComponent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras at dolor sit amet justo ultrices pulvinar. Cras ut tempus tortor. Quisque et orci pulvinar, rutrum eros quis, pretium urna. Quisque euismod rutrum eros, sit amet interdum quam iaculis posuere. Maecenas eget porttitor dolor, at dapibus nisl. Vivamus euismod nunc molestie."));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.chromatics.shift_info"));
        }
    }

    @java.lang.Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.addEffect(new EffectInstance(Effects.LEVITATION, 5, 10));
        BatEntity bat = new BatEntity(EntityType.BAT, worldIn);
        bat.setPos(playerIn.getX(),playerIn.getY(),playerIn.getZ());
        bat.addEffect(new EffectInstance(Effects.WITHER, 100, 10));
        worldIn.addFreshEntity(bat);
        return ActionResult.success(playerIn.getMainHandItem());
    }
}
