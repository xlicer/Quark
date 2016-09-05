/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 * 
 * File Created @ [05/09/2016, 01:16:11 (GMT)]
 */
package vazkii.quark.biomes;

import vazkii.quark.base.module.Module;
import vazkii.quark.biomes.feature.Meadow;

public class QuarkBiomes extends Module {

	@Override
	public void addFeatures() {
		registerFeature(new Meadow());
	}
	
}
