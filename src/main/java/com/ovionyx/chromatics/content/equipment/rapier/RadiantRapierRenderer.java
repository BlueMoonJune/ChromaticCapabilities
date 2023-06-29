package com.ovionyx.chromatics.content.equipment.rapier;

import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.ovionyx.chromatics.Chromatics;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModel;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModelRenderer;
import com.simibubi.create.foundation.item.render.PartialItemModelRenderer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.item.ItemStack;

import static java.lang.Math.sin;

public class RadiantRapierRenderer extends CustomRenderedItemModelRenderer {
    protected static final PartialModel GEAR = new PartialModel(Chromatics.asResource("item/radiant_rapier/gear"));
    protected static final PartialModel BLADE = new PartialModel(Chromatics.asResource("item/radiant_rapier/blade"));
    protected static final PartialModel GLOW = new PartialModel(Chromatics.asResource("item/radiant_rapier/glow"));
    protected static final PartialModel RADIANCE = new PartialModel(Chromatics.asResource("item/radiant_rapier/radiance"));
    protected static final PartialModel TANK = new PartialModel(Chromatics.asResource("item/radiant_rapier/tank"));

    @Override
    protected void render(ItemStack stack, CustomRenderedItemModel model, PartialItemModelRenderer renderer, ItemTransforms.TransformType transformType, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        int maxLight = 15728880;
        float worldTime = AnimationTickHolder.getRenderTime() / 20.0f;
        float step = 0.03125f;

        renderer.renderSolid(model.getOriginalModel(), light);
        renderer.renderSolidGlowing(RADIANCE.get(), maxLight);
        renderer.render(TANK.get(), light);

        float gearAngle = worldTime * -50f % 360;
        ms.mulPose(Vector3f.YP.rotationDegrees(gearAngle));

        renderer.renderSolid(GEAR.get(), light);

        ms.mulPose(Vector3f.YP.rotationDegrees(-gearAngle));

        float angle = worldTime * -180f % 360;

        ms.translate(sin(angle)*step,0,0);

        renderer.renderSolidGlowing(BLADE.get(), maxLight);
        renderer.renderGlowing(GLOW.get(), maxLight);
    }
}
