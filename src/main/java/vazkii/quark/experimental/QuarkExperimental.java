/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [10/06/2016, 18:05:33 (GMT)]
 */
package vazkii.quark.experimental;

import vazkii.quark.base.module.Module;
import vazkii.quark.experimental.features.BiggerCaves;
import vazkii.quark.experimental.features.Tabards;

public class QuarkExperimental extends Module {

	@Override
	public void addFeatures() {
		registerFeature(new BiggerCaves(), false);
		registerFeature(new Tabards(), false);
	}
	
	@Override
	public String getModuleDescription() {
		return "Experimental Features. All features in this module are disabled by default. Use at your own risk.";
	}
	
}
