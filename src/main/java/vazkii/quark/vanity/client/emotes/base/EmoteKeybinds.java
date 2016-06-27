/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [27/06/2016, 02:06:49 (GMT)]
 */
package vazkii.quark.vanity.client.emotes.base;

import java.util.HashMap;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public final class EmoteKeybinds {

	public static HashMap<KeyBinding, String> emoteKeys = new HashMap();
	
	public static void init() {
		for(String emoteName : EmoteHandler.emoteMap.keySet()) {
			KeyBinding key = new KeyBinding("quark.emote." + emoteName, 0, "quark.gui.emotesKeygroup");
			ClientRegistry.registerKeyBinding(key);
			emoteKeys.put(key, emoteName);
		}
	}
	
}
