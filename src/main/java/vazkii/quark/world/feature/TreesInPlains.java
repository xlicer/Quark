/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 *
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 *
 * File Created @ [06/06/2016, 00:56:47 (GMT)]
 */
package vazkii.quark.world.feature;

import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.quark.base.module.Feature;

public class TreesInPlains extends Feature {

	private WorldGenerator treeGen = new WorldGenBigTree(false);

	double treesPerChunk;
	boolean onlyBigTrees;

	@Override
	public void setupConfig() {
		treesPerChunk = loadPropDouble("Tree count per chunk", "Must be an integer if above 1. If below 1, works as a chance to generate one per chunk.", 0.1);
		onlyBigTrees = loadPropBool("Big trees only", "", true);
	}

	@SubscribeEvent
	public void decorate(DecorateBiomeEvent.Decorate event) {
		World world = event.getWorld();
		Biome biome = world.getBiomeGenForCoords(event.getPos());
		Random rand = event.getRand();

		if((biome == Biomes.PLAINS || biome == Biomes.ICE_PLAINS || biome == Biomes.MUTATED_PLAINS) && event.getType() == EventType.TREE) {
			if(treesPerChunk < 1 && rand.nextDouble() > treesPerChunk)
				return;

			int amount = (int) Math.max(1, treesPerChunk);
			for(int i = 0; i < amount; i++) {
				int x = rand.nextInt(16) + 8;
				int y = rand.nextInt(16) + 8;

				WorldGenerator gen = treeGen;
				if(!onlyBigTrees)
					gen = biome.genBigTreeChance(rand);
				gen.generate(world, rand, world.getHeight(event.getPos().add(x, 0, y)));
			}

			event.setResult(Result.DENY);
		}
	}

	@Override
	public boolean hasTerrainSubscriptions() {
		return true;
	}
}
