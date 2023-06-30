package com.ovionyx.chromatics.foundation.data.recipe.gen;

import com.google.common.base.Supplier;
import com.ovionyx.chromatics.Chromatics;
import com.ovionyx.chromatics.foundation.data.recipe.ChromaticsProcessingRecipeGen;
import com.ovionyx.chromatics.foundation.data.recipe.ChromaticsRecipeProvider;
import com.ovionyx.chromatics.init.AllItems;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.data.recipe.MechanicalCraftingRecipeBuilder;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.UnaryOperator;

public class MechanicalCrafting extends ChromaticsRecipeProvider {

    GeneratedRecipe
            CHAINSAW = create(AllItems.CHAINSAW::get).returns(1)
                    .recipe(b -> b
                            .key('P', Ingredient.of(AllBlocks.COGWHEEL.get()))
                            .key('S', Ingredient.of(M.shadowSteel()))
                            .key('R', Ingredient.of(M.refinedRadiance()))
                            .key('C', Ingredient.of(M.radiantCasing()))
                            .patternLine(" S    ")
                            .patternLine("SCPSRR")
                    ),
            DRILL = create(AllItems.DRILL::get).returns(1)
                    .recipe(b -> b
                            .key('P', Ingredient.of(AllBlocks.COGWHEEL.get()))
                            .key('S', Ingredient.of(M.shadowSteel()))
                            .key('R', Ingredient.of(M.refinedRadiance()))
                            .key('C', Ingredient.of(M.radiantCasing()))
                            .patternLine("  S   ")
                            .patternLine("SCSPSR")
                    ),
            JACKHAMMER = create(AllItems.JACKHAMMER::get).returns(1)
                    .recipe(b -> b
                            .key('S', Ingredient.of(M.shadowSteel()))
                            .key('R', Ingredient.of(M.refinedRadiance()))
                            .key('C', Ingredient.of(M.radiantCasing()))
                            .patternLine("SCS")
                            .patternLine("CSC")
                            .patternLine(" S ")
                            .patternLine(" R ")
                            .patternLine(" R ")
                    )
            ;

    public MechanicalCrafting(DataGenerator p_i48262_1_) {
        super(p_i48262_1_);
    }

    GeneratedRecipeBuilder create(Supplier<ItemLike> result) {
        return new GeneratedRecipeBuilder(result);
    }

    class GeneratedRecipeBuilder {

        private String suffix;
        private Supplier<ItemLike> result;
        private int amount;

        public GeneratedRecipeBuilder(Supplier<ItemLike> result) {
            this.suffix = "";
            this.result = result;
            this.amount = 1;
        }

        GeneratedRecipeBuilder returns(int amount) {
            this.amount = amount;
            return this;
        }

        GeneratedRecipeBuilder withSuffix(String suffix) {
            this.suffix = suffix;
            return this;
        }

        GeneratedRecipe recipe(UnaryOperator<MechanicalCraftingRecipeBuilder> builder) {
            return register(consumer -> {
                MechanicalCraftingRecipeBuilder b =
                        builder.apply(MechanicalCraftingRecipeBuilder.shapedRecipe(result.get(), amount));
                ResourceLocation location = Chromatics.asResource("mechanical_crafting/" + RegisteredObjects.getKeyOrThrow(result.get()
                                .asItem())
                        .getPath() + suffix);
                b.build(consumer, location);
            });
        }
    }

    @Override
    public String getName() {
        return "Chromatic Capabilities Mechanical Crafting Recipes";
    }

}
