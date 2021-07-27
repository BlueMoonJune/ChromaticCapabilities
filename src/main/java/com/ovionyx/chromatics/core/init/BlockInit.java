package com.ovionyx.chromatics.core.init;

import com.ovionyx.chromatics.Chromatics;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Chromatics.MODID);

    public static final RegistryObject<Block> CHROMATIC_CASING = BLOCKS.register("chromatic_casing",
            () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_YELLOW)
                    .strength(15f)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(0)
                    .sound(SoundType.STONE)));
}
