/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [18/03/2016, 22:37:10 (GMT)]
 */
package vazkii.quark.building;

import vazkii.quark.base.module.Module;
import vazkii.quark.building.feature.HardenedClayTiles;

public class QuarkBuilding extends Module {

	@Override
	public void addFeatures() {
		registerFeature(new HardenedClayTiles());
	}
	
}
