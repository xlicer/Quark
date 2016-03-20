/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [20/03/2016, 03:15:51 (GMT)]
 */
package vazkii.quark.tweaks.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import vazkii.quark.base.module.Feature;

public class StairsMakeMore extends Feature {

	int targetSize;
	int originalSize;
	
	@Override
	public void setupConfig() {
		targetSize = loadPropInt("Target stack size", "", 8);
		originalSize = loadPropInt("Vanilla stack size", "The stack size for the vanilla stair recipe, used for automatically detecting stair recipes", 4);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		for(IRecipe recipe : CraftingManager.getInstance().getRecipeList()) {
			ItemStack output = recipe.getRecipeOutput();
			if(output != null && output.stackSize == originalSize) {
				Item outputItem = output.getItem();
				Block outputBlock = Block.getBlockFromItem(outputItem);
				if(outputBlock != null && outputBlock instanceof BlockStairs)
					output.stackSize = targetSize;
			}
		}
	}
	
}
