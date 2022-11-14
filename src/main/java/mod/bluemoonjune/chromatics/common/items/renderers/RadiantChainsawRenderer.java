package mod.bluemoonjune.chromatics.common.items.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.bluemoonjune.chromatics.common.items.models.RadiantChainsawModel;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModelRenderer;
import com.simibubi.create.foundation.item.render.PartialItemModelRenderer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

import static java.lang.Math.*;

public class RadiantChainsawRenderer extends CustomRenderedItemModelRenderer<RadiantChainsawModel> {
    @Override
    protected void render(ItemStack stack, RadiantChainsawModel model, PartialItemModelRenderer renderer, ItemCameraTransforms.TransformType transformType, MatrixStack ms, IRenderTypeBuffer buffer, int light, int overlay) {
        int maxLight = 15728880;
        float worldTime = AnimationTickHolder.getRenderTime() / 20.0f;
        float step = 0.03125f;

        renderer.renderSolid(model.getOriginalModel(), light);
        renderer.renderSolidGlowing(model.getPartial("radiance"), maxLight);
        renderer.render(model.getPartial("tank"), light);

        float gearAngle = worldTime * -50f % 360;
        ms.translate(0,0,-5*step);
        ms.mulPose(Vector3f.YP.rotationDegrees(gearAngle));

        renderer.renderSolid(model.getPartial("gear"), light);

        ms.mulPose(Vector3f.YP.rotationDegrees(-gearAngle));
        ms.translate(0,0,5*step);

        float angle = worldTime * -50f % 360;

        ms.translate(sin(angle)*step,0,cos(angle)*step);

        renderer.renderSolidGlowing(model.getPartial("blade"), maxLight);
        renderer.renderGlowing(model.getPartial("blade_glow"), maxLight);
    }
}
