package com.ovionyx.chromatics.foundation.data.recipe;

import com.ovionyx.chromatics.foundation.data.recipe.gen.MechanicalCrafting;
import net.minecraft.data.DataGenerator;

import java.util.ArrayList;
import java.util.List;

public abstract class ChromaticsProcessingRecipeGen extends ChromaticsRecipeProvider{

    protected static final List<ChromaticsProcessingRecipeGen> GENERATORS = new ArrayList<>();
    public static void registerAll(DataGenerator gen) {
        //GENERATORS.add(new MechanicalCrafting(gen));
    }
    public ChromaticsProcessingRecipeGen(DataGenerator generator) {
        super(generator);
    }
}
