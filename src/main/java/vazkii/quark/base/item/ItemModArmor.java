/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [05/06/2016, 20:33:17 (GMT)]
 */
package vazkii.quark.base.item;

import java.util.List;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vazkii.quark.base.handler.TooltipHandler;
import vazkii.quark.base.lib.LibMisc;

public class ItemModArmor extends ItemArmor implements IVariantHolder {

	private final String bareName;

	public ItemModArmor(String name, ItemArmor.ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		
		setUnlocalizedName(name);
		bareName = name;
		ItemMod.variantHolders.add(this);
		setCreativeTab(CreativeTabs.COMBAT);
	}

	@Override
	public Item setUnlocalizedName(String name) {
		super.setUnlocalizedName(name);
		GameRegistry.register(this, new ResourceLocation(LibMisc.PREFIX_MOD + name));

		return this;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int dmg = par1ItemStack.getItemDamage();

		return "item." + LibMisc.PREFIX_MOD + bareName;
	}

	@Override
	public String[] getVariants() {
		return new String[] { bareName };
	}

	@Override
	public ItemMeshDefinition getCustomMeshDefinition() {
		return null;
	}

}
