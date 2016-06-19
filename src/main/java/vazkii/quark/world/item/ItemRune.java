/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [19/06/2016, 03:03:47 (GMT)]
 */
package vazkii.quark.world.item;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import vazkii.quark.base.handler.ICustomEnchantColor;
import vazkii.quark.base.item.IColorProvider;
import vazkii.quark.base.item.ItemMod;
import vazkii.quark.world.feature.ColorRunes;

public class ItemRune extends ItemMod implements IColorProvider, ICustomEnchantColor {

	private static final String[] VARIANTS = {
			"rune_white",
			"rune_orange",
			"rune_magenta",
			"rune_light_blue",
			"rune_yellow",
			"rune_lime",
			"rune_pink",
			"rune_gray",
			"rune_silver",
			"rune_cyan",
			"rune_purple",
			"rune_blue",
			"rune_brown",
			"rune_green",
			"rune_red",
			"rune_black"
	};
	
	public ItemRune() {
		super("rune", VARIANTS);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.MISC);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
	@Override
	public String getUniqueModel() {
		return "rune";
	}
	
	@Override
	public int getEnchantEffectColor(ItemStack stack) {
		return ItemDye.DYE_COLORS[15 - Math.min(15, stack.getItemDamage())];
	}

	@Override
	public IItemColor getColor() {
		return new IItemColor() {
			
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return tintIndex == 1 ? ColorRunes.getColorFromStack(stack) : 0xFFFFFF;
			}
		};
	}
	
}
