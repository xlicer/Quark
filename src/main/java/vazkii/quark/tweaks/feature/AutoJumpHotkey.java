/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 * 
 * File Created @ [06/07/2016, 17:13:46 (GMT)]
 */
package vazkii.quark.tweaks.feature;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.quark.base.module.Feature;

public class AutoJumpHotkey extends Feature {

	KeyBinding key;
	boolean down;
	
	@Override
	public void preInitClient(FMLPreInitializationEvent event) {
		key = new KeyBinding("quark.keybind.toggleAutojump", 48, "key.categories.movement");
		ClientRegistry.registerKeyBinding(key);
	}
	
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onKeyInput(RenderTickEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		boolean downNow = key.isKeyDown();
		if(mc.inGameHasFocus && !down && downNow) {
			boolean b = mc.gameSettings.getOptionOrdinalValue(Options.AUTO_JUMP);
			int i = b ? 1 : 0;
			mc.gameSettings.setOptionValue(Options.AUTO_JUMP, ~i & 1);
			mc.thePlayer.addChatComponentMessage(new TextComponentTranslation("quark.autojump" + i));
		}
		
		down = downNow;
	}
	
	@Override
	public boolean hasSubscriptions() {
		return isClient();
	}
	
}
