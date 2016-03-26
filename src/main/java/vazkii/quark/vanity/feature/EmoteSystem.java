/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [26/03/2016, 21:53:17 (GMT)]
 */
package vazkii.quark.vanity.feature;

import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.aurelienribon.tweenengine.Tween;
import vazkii.quark.base.module.Feature;
import vazkii.quark.vanity.client.emotes.EmoteNo;
import vazkii.quark.vanity.client.emotes.EmoteWave;
import vazkii.quark.vanity.client.emotes.EmoteYes;
import vazkii.quark.vanity.client.emotes.base.EmoteHandler;
import vazkii.quark.vanity.client.emotes.base.ModelAccessor;
import vazkii.quark.vanity.command.CommandEmote;

public class EmoteSystem extends Feature {

	@Override
	@SideOnly(Side.CLIENT)
	public void preInitClient(FMLPreInitializationEvent event) {
		Tween.registerAccessor(ModelBiped.class, new ModelAccessor());

		MinecraftForge.EVENT_BUS.register(new EmoteHandler.TickHandler());
		
		EmoteHandler.emoteMap.put("wave", EmoteWave.class);
		EmoteHandler.emoteMap.put("yes", EmoteYes.class);
		EmoteHandler.emoteMap.put("no", EmoteNo.class);
	}
	
	@Override
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandEmote());
	}
	
}
