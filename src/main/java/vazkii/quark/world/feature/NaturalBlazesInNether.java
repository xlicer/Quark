/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 *
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 *
 * File Created @ [01/06/2016, 20:37:07 (GMT)]
 */
package vazkii.quark.world.feature;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import vazkii.quark.base.module.Feature;

public class NaturalBlazesInNether extends Feature {

	int weight, min, max;

	@Override
	public void setupConfig() {
		weight = loadPropInt("Spawn Weight", "", 20);
		min = loadPropInt("Smallest spawn group", "", 1);
		max = loadPropInt("Largest spawn group", "", 3);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		Biomes.HELL.getSpawnableList(EnumCreatureType.MONSTER).add(new SpawnListEntry(EntityBlaze.class, weight, min, max));
	}
	
	@Override
	public boolean requiresMinecraftRestartToEnable() {
		return true;
	}

}
