/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [19/06/2016, 00:05:39 (GMT)]
 */
package vazkii.quark.tweaks.feature;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vazkii.quark.base.handler.RecipeHandler;
import vazkii.quark.base.module.Feature;

public class FlintTools extends Feature {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
	    String[][] patterns = new String[][] {{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}};
	    Item[] items = new Item[] { Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_AXE, Items.STONE_HOE };
	    
	    for(int i = 0; i < patterns.length; i++)
	    	RecipeHandler.addOreDictRecipe(new ItemStack(items[i]), 
	    			patterns[i][0], patterns[i][1], patterns[i][2], 
	    			'X', new ItemStack(Items.FLINT),
	    			'#', new ItemStack(Items.STICK));
	}
	
}
