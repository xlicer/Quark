/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 *
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 *
 * File Created @ [02/07/2016, 23:10:27 (GMT)]
 */
package vazkii.quark.world.client.layer;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;

public class LayerPirateClothes implements LayerRenderer<EntitySkeleton> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("quark", "textures/entity/pirate_overlay.png");
	private final RenderLivingBase<?> field_190093_b;
	private ModelSkeleton field_190094_c;

	public LayerPirateClothes(RenderLivingBase<?> p_i47131_1_) {
		field_190093_b = p_i47131_1_;
		field_190094_c = new ModelSkeleton(0.25F, true);
	}

	@Override
	public void doRenderLayer(EntitySkeleton entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		field_190094_c.setModelAttributes(field_190093_b.getMainModel());
		field_190094_c.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
		field_190093_b.bindTexture(TEXTURE);
		field_190094_c.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	@Override
	public boolean shouldCombineTextures() {
		return true;
	}

}
