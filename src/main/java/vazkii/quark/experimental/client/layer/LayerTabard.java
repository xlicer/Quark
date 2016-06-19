/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [19/06/2016, 04:41:28 (GMT)]
 */
package vazkii.quark.experimental.client.layer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.renderer.BannerTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.minecart.MinecartCollisionEvent;

public class LayerTabard implements LayerRenderer<AbstractClientPlayer> {

	private ModelBanner model = new ModelBanner();
	private TileEntityBanner banner = new TileEntityBanner();

	@Override
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.pushMatrix();
		if(entitylivingbaseIn.isSneaking()) {
			GlStateManager.rotate(28.64789F, 1.0F, 0.0F, 0.0F);
			GlStateManager.translate(0F, 0.15F, -0.1F);
		}

		ItemStack mainhand = entitylivingbaseIn.getHeldItemMainhand();
		if(mainhand != null && mainhand.getItem() == Items.BANNER)
			banner.setItemValues(mainhand);
		renderBanner(banner, partialTicks);
		GlStateManager.translate(0F, 0F, 0.38F);
		renderBanner(banner, partialTicks);
		

		GlStateManager.popMatrix();
	}

	public void renderBanner(TileEntityBanner te, float partialTicks) {
		boolean flag = te.getWorld() != null;
		int i = flag ? te.getBlockMetadata() : 0;
		long j = flag ? te.getWorld().getTotalWorldTime() : 0L;
		GlStateManager.pushMatrix();
		float f = 0.6666667F;

		GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);
		GlStateManager.translate(0F, -0.85F, 0.15F);
		float s = 0.6F;
		GlStateManager.scale(s, s, s);
		model.bannerStand.showModel = false;
		model.bannerTop.showModel = false;
		
		BlockPos blockpos = te.getPos();
		float f3 = (float)(blockpos.getX() * 7 + blockpos.getY() * 9 + blockpos.getZ() * 13) + (float)j + partialTicks;
		model.bannerSlate.rotateAngleX = (-0.0125F + 0.01F * MathHelper.cos(f3 * (float)Math.PI * 0.02F)) * (float)Math.PI;
		GlStateManager.enableRescaleNormal();
		ResourceLocation resourcelocation = this.getBannerResourceLocation(te);

		if(resourcelocation != null) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(resourcelocation);
			GlStateManager.pushMatrix();
			GlStateManager.scale(f, -f, -f);
			model.renderBanner();
			GlStateManager.popMatrix();
		}

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.popMatrix();
	}

    private ResourceLocation getBannerResourceLocation(TileEntityBanner bannerObj) {
        return BannerTextures.BANNER_DESIGNS.getResourceLocation(bannerObj.getPatternResourceLocation(), bannerObj.getPatternList(), bannerObj.getColorList());
    }
	
	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
