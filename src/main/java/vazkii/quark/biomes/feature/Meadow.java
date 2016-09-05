/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 * 
 * File Created @ [05/09/2016, 01:35:50 (GMT)]
 */
package vazkii.quark.biomes.feature;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vazkii.quark.biomes.biome.BiomeMeadow;

public class Meadow extends AbstrBiomeFeature {

	@Override
	public Biome makeBiome() {
		return new BiomeMeadow();
	}
	
}
