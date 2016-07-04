/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 * 
 * File Created @ [05/06/2016, 20:38:24 (GMT)]
 */
package vazkii.quark.vanity.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import vazkii.quark.base.client.model.ModelQuarkArmor;

public class ModelWitchHat extends ModelQuarkArmor {

	private ModelRenderer witchHat;
	
	public ModelWitchHat() {
		textureWidth = 64;
		textureHeight = 128;
		
		float offX = -5F;
		float offY = -10F;
		float offZ = -5F;
		
        witchHat = new ModelRenderer(this);
        witchHat.setTextureSize(64, 128);
        witchHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
        witchHat.setTextureOffset(0, 64).addBox(offX, offY, offZ, 10, 2, 10);
        ModelRenderer modelrenderer = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelrenderer.setRotationPoint(1.75F, -3.8F, 2.0F);
        modelrenderer.setTextureOffset(0, 76).addBox(offX, offY, offZ, 7, 4, 7);
        modelrenderer.rotateAngleX = -0.05235988F;
        modelrenderer.rotateAngleZ = 0.02617994F;
        witchHat.addChild(modelrenderer);
        ModelRenderer modelrenderer1 = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelrenderer1.setRotationPoint(1.75F, -3.0F, 2.0F);
        modelrenderer1.setTextureOffset(0, 87).addBox(offX, offY, offZ, 4, 4, 4);
        modelrenderer1.rotateAngleX = -0.10471976F;
        modelrenderer1.rotateAngleZ = 0.05235988F;
        modelrenderer.addChild(modelrenderer1);
        ModelRenderer modelrenderer2 = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelrenderer2.setRotationPoint(1.0F, -1.0F, 0F);
        modelrenderer2.setTextureOffset(0, 95).addBox(offX, offY, offZ, 1, 2, 1, 0.25F);
        modelrenderer2.rotateAngleX = -0.20943952F;
        modelrenderer2.rotateAngleZ = 0.10471976F;
        modelrenderer1.addChild(modelrenderer2);
	}
	
	@Override
	public void setModelParts() {
		bipedHead.showModel = false;
		bipedHeadwear = witchHat;
	}
	
}
