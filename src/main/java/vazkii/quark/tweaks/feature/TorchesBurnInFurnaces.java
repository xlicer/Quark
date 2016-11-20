package vazkii.quark.tweaks.feature;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vazkii.quark.base.module.Feature;

public class TorchesBurnInFurnaces extends Feature {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		GameRegistry.registerFuelHandler((ItemStack stack) -> stack.getItem() == Item.getItemFromBlock(Blocks.TORCH) ? 400 : 0);
	}
	
}
