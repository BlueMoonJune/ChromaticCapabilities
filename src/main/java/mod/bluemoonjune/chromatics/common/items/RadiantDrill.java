package mod.bluemoonjune.chromatics.common.items;

import com.simibubi.create.content.curiosities.armor.BackTankUtil;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
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

    public RadiantDrill(IItemTier p_i48469_1_, float p_i48469_2_, float p_i48469_3_, Properties p_i48469_4_) {
        super(p_i48469_1_, p_i48469_2_, p_i48469_3_, p_i48469_4_);
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
}
