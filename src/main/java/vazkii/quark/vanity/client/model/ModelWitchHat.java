/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [05/06/2016, 20:38:24 (GMT)]
 */
package vazkii.quark.vanity.client.model;

import org.lwjgl.opengl.GL11;

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
import net.minecraft.util.datafix.fixes.EntityArmorAndHeld;

public class ModelWitchHat extends ModelBiped {

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
	
	public void setModelParts() {
		bipedHeadwear = witchHat;
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		// Fixes rendering on armor stands
		bipedHead.showModel = false;
		bipedHeadwear = witchHat;
		
		GlStateManager.pushMatrix();
		if(entity instanceof EntityArmorStand) {
			f3 = 0;
			GlStateManager.translate(0F, 0.15F, 0F);
		}
		prepareForRender(entity, f5);
		super.render(entity, f, f1, f2, f3, f4, f5);
		GlStateManager.popMatrix();
	}
	
	public void prepareForRender(Entity entity, float pticks) {
		EntityLivingBase living = (EntityLivingBase) entity;
		isSneak = living != null ? living.isSneaking() : false;
		isChild = living != null ? living.isChild() : false;
		if(living != null && living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			
			swingProgress = player.getSwingProgress(pticks);
			
            ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.EMPTY;
            ModelBiped.ArmPose modelbiped$armpose1 = ModelBiped.ArmPose.EMPTY;
            ItemStack itemstack = player.getHeldItemMainhand();
            ItemStack itemstack1 = player.getHeldItemOffhand();
            
            if(itemstack != null) {
                modelbiped$armpose = ModelBiped.ArmPose.ITEM;

                if(player.getItemInUseCount() > 0) {
                    EnumAction enumaction = itemstack.getItemUseAction();

                    if(enumaction == EnumAction.BLOCK)
                        modelbiped$armpose = ModelBiped.ArmPose.BLOCK;
                    else if(enumaction == EnumAction.BOW)
                        modelbiped$armpose = ModelBiped.ArmPose.BOW_AND_ARROW;
                }
            }

            if(itemstack1 != null) {
                modelbiped$armpose1 = ModelBiped.ArmPose.ITEM;

                if(player.getItemInUseCount() > 0) {
                    EnumAction enumaction1 = itemstack1.getItemUseAction();

                    if(enumaction1 == EnumAction.BLOCK)
                        modelbiped$armpose1 = ModelBiped.ArmPose.BLOCK;
                }
            }

            if(player.getPrimaryHand() == EnumHandSide.RIGHT) {
                rightArmPose = modelbiped$armpose;
                leftArmPose = modelbiped$armpose1;
            } else {
                rightArmPose = modelbiped$armpose1;
                leftArmPose = modelbiped$armpose;
            }
		}
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
}
