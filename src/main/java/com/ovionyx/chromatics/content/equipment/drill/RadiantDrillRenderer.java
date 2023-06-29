package com.ovionyx.chromatics.content.equipment.drill;

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

public class RadiantDrillRenderer extends CustomRenderedItemModelRenderer {
    protected static final PartialModel GEAR = new PartialModel(Chromatics.asResource("item/radiant_drill/gear"));
    protected static final PartialModel DRILL_BASE = new PartialModel(Chromatics.asResource("item/radiant_drill/drill_base"));
    protected static final PartialModel DRILL = new PartialModel(Chromatics.asResource("item/radiant_drill/drill"));
    protected static final PartialModel TANK = new PartialModel(Chromatics.asResource("item/radiant_drill/tank"));
    protected static final PartialModel RADIANCE = new PartialModel(Chromatics.asResource("item/radiant_drill/radiance"));
    protected static final PartialModel DRILL_GLOW = new PartialModel(Chromatics.asResource("item/radiant_drill/drill_glow"));

    @Override
    protected void render(ItemStack stack, CustomRenderedItemModel model, PartialItemModelRenderer renderer, ItemTransforms.TransformType transformType, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        int maxLight = 15728880;
        float worldTime = AnimationTickHolder.getRenderTime() / 20.0f;

        renderer.renderSolid(model.getOriginalModel(), light);
        renderer.renderSolidGlowing(RADIANCE.get(), maxLight);
        renderer.render(TANK.get(), light);

        float gearAngle = worldTime * -50f % 360;
        ms.mulPose(Vector3f.ZP.rotationDegrees(gearAngle));

        renderer.renderSolid(GEAR.get(), light);

        float angle = worldTime * -500f % 360;
        ms.mulPose(Vector3f.ZP.rotationDegrees(angle));

        renderer.renderSolid(DRILL_BASE.get(), light);
        renderer.renderSolidGlowing(DRILL.get(), maxLight);
        renderer.renderGlowing(DRILL_GLOW.get(), maxLight);
    }
}
