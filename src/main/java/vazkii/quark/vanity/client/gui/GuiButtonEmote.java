/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [27/03/2016, 00:14:28 (GMT)]
 */
package vazkii.quark.vanity.client.gui;

import net.minecraft.client.resources.I18n;
import vazkii.quark.base.client.gui.GuiButtonTranslucent;

public class GuiButtonEmote extends GuiButtonTranslucent {

	public final String emote;
	
	public GuiButtonEmote(int buttonId, int x, int y, int widthIn, int heightIn, String emote) {
		super(buttonId, x, y, widthIn, heightIn, I18n.format("quark.emote." + emote));
		this.emote = emote;
	}

}
