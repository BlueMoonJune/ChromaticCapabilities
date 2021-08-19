package com.ovionyx.chromatics.common.items.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.ovionyx.chromatics.common.items.models.RadiantJackhammerModel;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModelRenderer;
import com.simibubi.create.foundation.item.render.PartialItemModelRenderer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class RadiantJackhammerRenderer extends CustomRenderedItemModelRenderer<RadiantJackhammerModel> {
    @Override
    protected void render(ItemStack stack, RadiantJackhammerModel model, PartialItemModelRenderer renderer, ItemCameraTransforms.TransformType transformType, MatrixStack ms, IRenderTypeBuffer buffer, int light, int overlay) {
        int maxLight = 15728880;
        float worldTime = AnimationTickHolder.getRenderTime() / 20.0f;
        float step = 0.03125f;

        renderer.renderSolid(model.getOriginalModel(), light);
        renderer.renderSolidGlowing(model.getPartial("radiance"), maxLight);
        renderer.render(model.getPartial("tank"), light);

        float gearAngle = worldTime * -50f % 360;
        //ms.translate(0, 0, -7*step);
        ms.mulPose(Vector3f.YP.rotationDegrees(gearAngle));

        renderer.renderSolid(model.getPartial("gear"), light);

        float angle = worldTime * -50f % 360;


        ms.mulPose(Vector3f.YP.rotationDegrees(-gearAngle));
        //ms.translate(0, 0, 7*step);

        ms.translate(0,0,cos(angle)*step);

        renderer.renderSolidGlowing(model.getPartial("chisel"), maxLight);
        renderer.renderGlowing(model.getPartial("chisel_glow"), maxLight);
    }
}
