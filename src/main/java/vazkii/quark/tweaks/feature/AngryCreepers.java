/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [19/03/2016, 16:37:16 (GMT)]
 */
package vazkii.quark.tweaks.feature;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.quark.base.module.Feature;

public class AngryCreepers extends Feature {

	@Override
	@SideOnly(Side.CLIENT)
	public void preInitClient(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityCreeper.class, manager -> new RenderCreeperRed(manager));
	}
	
	private static class RenderCreeperRed extends RenderCreeper {

		public RenderCreeperRed(RenderManager renderManagerIn) {
			super(renderManagerIn);
		}
		
		@Override
		protected ResourceLocation getEntityTexture(EntityCreeper entity) {
			if(entity.getCreeperState() == 1) {
				float antired = 1F - (entity.getCreeperFlashIntensity(0F) / 1.17F + 0.1F);
				GL11.glColor3f(1F, antired, antired);
			}
			return super.getEntityTexture(entity);
		}
		
	}
	
	
}
