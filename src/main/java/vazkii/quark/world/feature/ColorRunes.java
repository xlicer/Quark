/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [19/06/2016, 01:13:02 (GMT)]
 */
package vazkii.quark.world.feature;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.quark.base.handler.ItemNBTHelper;
import vazkii.quark.base.module.Feature;
import vazkii.quark.base.module.ModuleLoader;

public class ColorRunes extends Feature {

	public static final String TAG_RUNE_ATTACHED = "Quark:RuneAttached";
	public static final String TAG_RUNE_COLOR = "Quark:RuneColor";
	private static ItemStack targetStack;
	
	// Called from ASM. See ClassTransformer
	public static void setTargetStack(ItemStack stack) {
		targetStack = stack;
	}
	
	// Called from ASM. See ClassTransformer
	public static int getColor() {
		if(!ModuleLoader.isFeatureEnabled(ColorRunes.class) || !doesStackHaveRune(targetStack))
			return -8372020;
		
		return 0xff880000; // TODO
	}
	
	// Called from ASM. See ClassTransformer
	public static void applyColor(float f1, float f2, float f3, float f4) {
		if(!ModuleLoader.isFeatureEnabled(ColorRunes.class) || !doesStackHaveRune(targetStack))
			GlStateManager.color(f1, f2, f3, f4);
		
		GlStateManager.color(0.7F, 0F, 0F); // TODO
	}
	
	public static boolean doesStackHaveRune(ItemStack stack) {
		return stack == null || !stack.hasTagCompound() || ItemNBTHelper.getBoolean(stack, TAG_RUNE_ATTACHED, false);
	}
	
}
