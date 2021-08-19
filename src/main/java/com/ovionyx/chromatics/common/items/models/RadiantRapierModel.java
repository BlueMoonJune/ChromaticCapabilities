package com.ovionyx.chromatics.common.items.models;

import com.ovionyx.chromatics.common.items.renderers.RadiantChainsawRenderer;
import com.ovionyx.chromatics.common.items.renderers.RadiantRapierRenderer;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.util.ResourceLocation;

public class RadiantRapierModel extends CustomRenderedItemModel {


    private ResourceLocation getPartialModelLocation(String name) {
        return new ResourceLocation("chromatics", "item/" + basePath + "/" + name);
    }

    public RadiantRapierModel(IBakedModel template) {
        super(template, "rapier");
        this.addPartials(new String[]{"gear", "radiance", "blade", "tank", "glow"});
    }

    @Override
    public ItemStackTileEntityRenderer createRenderer() {
        return new RadiantRapierRenderer();
    }
}
