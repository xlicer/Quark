/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 * 
 * File Created @ [05/09/2016, 01:31:53 (GMT)]
 */
package vazkii.quark.biomes.feature;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vazkii.quark.base.module.Feature;

public abstract class AbstrBiomeFeature extends Feature {

	public int weight;
	public boolean biomeOnlyMode;
	
	@Override
	public void setupConfig() {
		weight = loadPropInt("Spawn Weight", "", weight);
		
		if(hasBiomeOnlyMode())
			biomeOnlyMode = loadPropBool("Biome Only", "Only adds the biome and none of the blocks/items that come with it", false);
	}
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		registerBiome();
	}
	
	public void registerBiome() {
		Biome b = makeBiome();
		
		Biome.registerBiome(getNextFreeBiomeId(), b.getRegistryName().toString(), b);
		
		for(BiomeType type : BiomeType.values())
			if(spawnsInType(type))
				BiomeManager.addBiome(type, new BiomeEntry(b, getDefaultWeight()));
		
		if(isSpawnBiome())
			BiomeManager.addSpawnBiome(b);
		if(isVillageBiome())
			BiomeManager.addVillageBiome(b, true);
	}
	
	public abstract Biome makeBiome();
	
	public int getDefaultWeight() {
		return 10;
	}
	
	public boolean spawnsInType(BiomeType type) {
		return type == BiomeType.WARM;
	}
	
	public boolean isSpawnBiome() {
		return false;
	}
	
	public boolean isVillageBiome() {
		return false;
	}
	
	public boolean hasBiomeOnlyMode() {
		return false;
	}
	
	// Taken from BiomesOPlenty
	// https://github.com/Glitchfiend/BiomesOPlenty/blob/1e2191a4551147234ea193f756bb1c231f63ece5/src/main/java/biomesoplenty/common/init/ModBiomes.java
    private static int nextBiomeId = 40;
	public static int getNextFreeBiomeId() {
        for(int i = nextBiomeId; i < 256; i++) {
            if(Biome.getBiome(i) != null) {
                if(i == 255) 
                	throw new IllegalArgumentException("There are no more biome ids avaliable!");
                
                continue;
            } else {
                nextBiomeId = i + 1;
                return i;
            }
        }

        return -1;
    }
}
