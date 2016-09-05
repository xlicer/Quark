/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 * 
 * File Created @ [05/09/2016, 01:36:08 (GMT)]
 */
package vazkii.quark.biomes.biome;

import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomePlains;
import vazkii.quark.base.lib.LibMisc;

public class BiomeMeadow extends BiomePlains {

	public BiomeMeadow() {
		super(true, makeProps());
		theBiomeDecorator.flowersPerChunk = 8;
		theBiomeDecorator.grassPerChunk = 20;
		theBiomeDecorator.field_189870_A = 0; // large tree chance
	
		spawnableCreatureList.clear();
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 5, 3, 8));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 12, 8, 8));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 10, 8, 8));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 10, 8, 8));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCow.class, 8, 8, 8));
        
        setRegistryName(LibMisc.PREFIX_MOD + "meadow");
	}
	
	public static BiomeProperties makeProps() {
		BiomeProperties props = new BiomeProperties("Meadow");
		props.setBaseHeight(0.125F);
		props.setHeightVariation(0.025F);
		props.setTemperature(0.9F);
		props.setRainfall(0.6F);
		return props;
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
        return getModdedBiomeGrassColor(0x59D822);
	}
	

}
