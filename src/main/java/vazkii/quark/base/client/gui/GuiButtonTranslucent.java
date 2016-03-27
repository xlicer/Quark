/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [27/03/2016, 00:02:53 (GMT)]
 */
package vazkii.quark.base.client.gui;

import net.minecraft.client.gui.GuiButton;

public class GuiButtonTranslucent extends GuiButton {

	public GuiButtonTranslucent(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}
	
    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
    	drawRect(x, y, x + width, y + height, Integer.MIN_VALUE);
    }


}
