/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [02/07/2016, 21:09:48 (GMT)]
 */
package vazkii.quark.world.client.layer;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.SkeletonType;
import net.minecraft.util.ResourceLocation;

public class LayerAshenClothes implements LayerRenderer<EntitySkeleton> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation("quark", "textures/entity/ashen_overlay.png");
	private final RenderLivingBase<?> field_190093_b;
	private ModelSkeleton field_190094_c;

	public LayerAshenClothes(RenderLivingBase<?> p_i47131_1_) {
		this.field_190093_b = p_i47131_1_;
		this.field_190094_c = new ModelSkeleton(0.25F, true);
	}

	@Override
	public void doRenderLayer(EntitySkeleton entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.field_190094_c.setModelAttributes(this.field_190093_b.getMainModel());
		this.field_190094_c.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
		this.field_190093_b.bindTexture(TEXTURE);
		this.field_190094_c.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	@Override
	public boolean shouldCombineTextures() {
		return true;
	}
}

