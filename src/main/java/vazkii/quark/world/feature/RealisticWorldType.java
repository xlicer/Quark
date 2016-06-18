/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [18/06/2016, 22:18:02 (GMT)]
 */
package vazkii.quark.world.feature;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import vazkii.quark.base.module.Feature;
import vazkii.quark.world.world.WorldTypeRealistic;

public class RealisticWorldType extends Feature {

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		new WorldTypeRealistic("quark_realistic");
	}
	
	@Override
	public String getFeatureDescription() {
		return "Allows for usage of a new Realistic world type, made by /u/Soniop.\n"
				+ "https://www.reddit.com/r/Minecraft/comments/4nfw3t/more_realistic_generation_preset_in_comment/\n"
				+ "If you want to use it in multiplayer, set the world type to \"quark_realistic\"";
	}
	
}
