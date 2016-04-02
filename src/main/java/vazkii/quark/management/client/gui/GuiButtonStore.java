/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [28/03/2016, 15:59:35 (GMT)]
 */
package vazkii.quark.management.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import vazkii.quark.management.feature.StoreToChests;

public class GuiButtonStore extends GuiButton {

	private static ResourceLocation icon_store = new ResourceLocation("quark", "textures/misc/icon_store.png"); 
	private static ResourceLocation icon_store_hover = new ResourceLocation("quark", "textures/misc/icon_store_hover.png"); 
	private static ResourceLocation icon_store_smart = new ResourceLocation("quark", "textures/misc/icon_store_smart.png"); 
	private static ResourceLocation icon_store_smart_hover = new ResourceLocation("quark", "textures/misc/icon_store_smart_hover.png");
	
	public GuiButtonStore(int id, int par2, int par3) {
		super(id, par2, par3, 16, 16, "");
	}

	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
		if(enabled) {
			hovered = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width && par3 < yPosition + height;
			int k = getHoverState(hovered);

			ResourceLocation res = null;
			
			if(GuiScreen.isShiftKeyDown() != StoreToChests.invert) {
				if(k == 2)
					res = icon_store_smart_hover;
				else res = icon_store_smart;
			} else {
				if(k == 2)
					res = icon_store_hover;
				else res = icon_store;
			}
			
			par1Minecraft.renderEngine.bindTexture(res);
			GlStateManager.color(1F, 1F, 1F, 1F);
			drawModalRectWithCustomSizedTexture(xPosition, yPosition, 0, 0, 16, 16, 16, 16);
		}
	}

}
