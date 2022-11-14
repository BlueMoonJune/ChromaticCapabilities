package mod.bluemoonjune.chromatics.common.items.models;

import mod.bluemoonjune.chromatics.common.items.renderers.RadiantDrillRenderer;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.util.ResourceLocation;

public class RadiantDrillModel extends CustomRenderedItemModel {


    private ResourceLocation getPartialModelLocation(String name) {
        return new ResourceLocation("chromatics", "item/" + basePath + "/" + name);
    }

    public RadiantDrillModel(IBakedModel template) {
        super(template, "drill");
        this.addPartials(new String[]{"gear", "radiance", "drill_base", "drill", "tank", "drill_glow"});
    }

    @Override
    public ItemStackTileEntityRenderer createRenderer() {
        return new RadiantDrillRenderer();
    }
}
