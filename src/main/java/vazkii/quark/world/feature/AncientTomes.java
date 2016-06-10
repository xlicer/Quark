/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [01/06/2016, 19:41:33 (GMT)]
 */
package vazkii.quark.world.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.quark.base.module.Feature;
import vazkii.quark.world.item.ItemAncientTome;

public class AncientTomes extends Feature {

	public static Item ancient_tome;
	public static List<Enchantment> validEnchants = new ArrayList();
	private String[] enchantNames;
	
	int dungeonWeight, libraryWeight, itemQuality;

	@Override
	public void setupConfig() {
		enchantNames = loadPropStringList("Valid Enchantments", "", new String[] {
				Enchantments.FEATHER_FALLING.getRegistryName().toString(),
				Enchantments.RESPIRATION.getRegistryName().toString(),
				Enchantments.THORNS.getRegistryName().toString(),
				Enchantments.DEPTH_STRIDER.getRegistryName().toString(),
				Enchantments.SHARPNESS.getRegistryName().toString(),
				Enchantments.SMITE.getRegistryName().toString(),
				Enchantments.BANE_OF_ARTHROPODS.getRegistryName().toString(),
				Enchantments.KNOCKBACK.getRegistryName().toString(),
				Enchantments.FIRE_ASPECT.getRegistryName().toString(),
				Enchantments.LOOTING.getRegistryName().toString(),
				Enchantments.EFFICIENCY.getRegistryName().toString(),
				Enchantments.UNBREAKING.getRegistryName().toString(),
				Enchantments.FORTUNE.getRegistryName().toString(),
				Enchantments.POWER.getRegistryName().toString(),
				Enchantments.PUNCH.getRegistryName().toString(),
				Enchantments.LUCK_OF_THE_SEA.getRegistryName().toString(),
				Enchantments.LURE.getRegistryName().toString(),
		});

		dungeonWeight = loadPropInt("Dungeon loot weight", "", 8);
		libraryWeight = loadPropInt("Stronghold Library loot weight", "", 12);
		itemQuality = loadPropInt("Item quality for loot", "", 2);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ancient_tome = new ItemAncientTome();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		validEnchants.clear();
		for(String s : enchantNames) {
			ResourceLocation r = new ResourceLocation(s);
			Enchantment e = Enchantment.REGISTRY.getObject(r);
			if(e != null)
				validEnchants.add(e);
		}
	}

	@Override
	public boolean hasSubscriptions() {
		return true;
	}

	@SubscribeEvent
	public void onLootTableLoad(LootTableLoadEvent event) {
		if(event.getName().equals(LootTableList.CHESTS_STRONGHOLD_LIBRARY))
			event.getTable().getPool("main").addEntry(new LootEntryItem(ancient_tome, libraryWeight, itemQuality, new LootFunction[] { new EnchantTomeFunction() }, new LootCondition[0], "quark:ancient_tome"));
		else if(event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON))
			event.getTable().getPool("main").addEntry(new LootEntryItem(ancient_tome, dungeonWeight, itemQuality, new LootFunction[] { new EnchantTomeFunction() }, new LootCondition[0], "quark:ancient_tome"));
	}

	@SubscribeEvent
	public void onAnvilUpdate(AnvilUpdateEvent event) {
		ItemStack left = event.getLeft();
		ItemStack right = event.getRight();

		if(left != null && right != null) {
			if(left.getItem() == Items.ENCHANTED_BOOK && right.getItem() == ancient_tome)
				handleTome(left, right, event);
			else if(right.getItem() == Items.ENCHANTED_BOOK && left.getItem() == ancient_tome)
				handleTome(right, left, event);

			else if(right.getItem() == Items.ENCHANTED_BOOK) {
				Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(right);
				if(enchants.size() == 1) {
					Enchantment ench = enchants.keySet().iterator().next();
					int level = enchants.get(ench);

					if(level > ench.getMaxLevel() && ench.canApply(left)) {
						ItemStack out = left.copy();

						Map<Enchantment, Integer> currentEnchants = EnchantmentHelper.getEnchantments(out);
						currentEnchants.put(ench, level);
						EnchantmentHelper.setEnchantments(currentEnchants, out);

						event.setOutput(out);
						event.setCost(35);
					}
				}
			}
		}
	}

	private void handleTome(ItemStack book, ItemStack tome, AnvilUpdateEvent event) {
		Map<Enchantment, Integer> enchantsLeft = EnchantmentHelper.getEnchantments(book);
		Map<Enchantment, Integer> enchantsRight = EnchantmentHelper.getEnchantments(tome);

		if(enchantsLeft.equals(enchantsRight)) {
			Enchantment ench = enchantsRight.keySet().iterator().next();
			ItemStack output = new ItemStack(Items.ENCHANTED_BOOK);
			((ItemEnchantedBook) output.getItem()).addEnchantment(output, new EnchantmentData(ench, enchantsRight.get(ench) + 1));
			event.setOutput(output);
			event.setCost(35);
		}
	}

	public static class EnchantTomeFunction extends LootFunction {

		protected EnchantTomeFunction() {
			super(new LootCondition[0]);
		}

		@Override
		public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
			Enchantment enchantment = validEnchants.get(rand.nextInt(validEnchants.size()));
			stack.addEnchantment(enchantment, enchantment.getMaxLevel());
			return stack;
		}

	}

}
