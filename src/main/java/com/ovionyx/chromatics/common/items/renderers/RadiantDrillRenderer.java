package com.ovionyx.chromatics.common.items.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.ovionyx.chromatics.common.items.models.RadiantDrillModel;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModelRenderer;
import com.simibubi.create.foundation.item.render.PartialItemModelRenderer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class RadiantDrillRenderer extends CustomRenderedItemModelRenderer<RadiantDrillModel> {
    @Override
    protected void render(ItemStack stack, RadiantDrillModel model, PartialItemModelRenderer renderer, ItemCameraTransforms.TransformType transformType, MatrixStack ms, IRenderTypeBuffer buffer, int light, int overlay) {
        int maxLight = 15728880;
        float worldTime = AnimationTickHolder.getRenderTime() / 20.0f;

        renderer.renderSolid(model.getOriginalModel(), light);
        renderer.renderSolidGlowing(model.getPartial("radiance"), maxLight);
        renderer.render(model.getPartial("tank"), light);

        float gearAngle = worldTime * -50f % 360;
        ms.mulPose(Vector3f.ZP.rotationDegrees(gearAngle));

        renderer.renderSolid(model.getPartial("gear"), light);

        float angle = worldTime * -500f % 360;
        ms.mulPose(Vector3f.ZP.rotationDegrees(angle));

        renderer.renderSolid(model.getPartial("drill_base"), light);
        renderer.renderSolidGlowing(model.getPartial("drill"), maxLight);
        renderer.renderGlowing(model.getPartial("drill_glow"), maxLight);
    }
}
