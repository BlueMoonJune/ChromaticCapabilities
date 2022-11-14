package mod.bluemoonjune.chromatics.common.items.models;

import mod.bluemoonjune.chromatics.common.items.renderers.RadiantChainsawRenderer;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.util.ResourceLocation;

public class RadiantChainsawModel extends CustomRenderedItemModel {


    private ResourceLocation getPartialModelLocation(String name) {
        return new ResourceLocation("chromatics", "item/" + basePath + "/" + name);
    }

    public RadiantChainsawModel(IBakedModel template) {
        super(template, "chainsaw");
        this.addPartials(new String[]{"gear", "radiance", "blade", "tank", "blade_glow"});
    }

    @Override
    public ItemStackTileEntityRenderer createRenderer() {
        return new RadiantChainsawRenderer();
    }
}
