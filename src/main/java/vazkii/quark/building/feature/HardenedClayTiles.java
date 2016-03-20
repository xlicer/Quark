/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [19/03/2016, 01:24:16 (GMT)]
 */
package vazkii.quark.building.feature;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vazkii.quark.base.handler.RecipeHandler;
import vazkii.quark.base.module.Feature;
import vazkii.quark.building.block.BlockHardenedClayTiles;
import vazkii.quark.building.block.BlockStainedClayTiles;

public class HardenedClayTiles extends Feature {

	public static Block hardened_clay_tiles;
	public static Block stained_clay_tiles;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		hardened_clay_tiles = new BlockHardenedClayTiles();
		stained_clay_tiles = new BlockStainedClayTiles();

		RecipeHandler.addOreDictRecipe(new ItemStack(hardened_clay_tiles, 4, 0), 
				"BB", "BB",
				'B', new ItemStack(Blocks.hardened_clay));
		
		for(int i = 0; i < 16; i++)
			RecipeHandler.addOreDictRecipe(new ItemStack(stained_clay_tiles, 4, i), 
					"BB", "BB",
					'B', new ItemStack(Blocks.stained_hardened_clay, 1, i));
	}

}
