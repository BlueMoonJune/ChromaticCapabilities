package mod.bluemoonjune.chromatics.common.items.models;

import mod.bluemoonjune.chromatics.common.items.renderers.RadiantKnifeRenderer;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.util.ResourceLocation;

public class RadiantKnifeModel extends CustomRenderedItemModel {


    private ResourceLocation getPartialModelLocation(String name) {
        return new ResourceLocation("chromatics", "item/" + basePath + "/" + name);
    }

    public RadiantKnifeModel(IBakedModel template) {
        super(template, "knife");
        this.addPartials(new String[]{"gear", "radiance", "blade", "tank", "glow"});
    }

    @Override
    public ItemStackTileEntityRenderer createRenderer() {
        return new RadiantKnifeRenderer();
    }
}
