package mod.bluemoonjune.chromatics.common.items.models;

import mod.bluemoonjune.chromatics.common.items.renderers.RadiantJackhammerRenderer;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.util.ResourceLocation;

public class RadiantJackhammerModel extends CustomRenderedItemModel {


    private ResourceLocation getPartialModelLocation(String name) {
        return new ResourceLocation("chromatics", "item/" + basePath + "/" + name);
    }

    public RadiantJackhammerModel(IBakedModel template) {
        super(template, "jackhammer");
        this.addPartials(new String[]{"gear", "radiance", "chisel", "tank", "chisel_glow"});
    }

    @Override
    public ItemStackTileEntityRenderer createRenderer() {
        return new RadiantJackhammerRenderer();
    }
}
