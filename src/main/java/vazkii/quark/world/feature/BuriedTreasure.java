/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [30/04/2016, 18:34:27 (GMT)]
 */
package vazkii.quark.world.feature;


import java.util.Random;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.quark.base.handler.ItemNBTHelper;
import vazkii.quark.base.module.Feature;

public class BuriedTreasure extends Feature {

	public static String TAG_TREASURE_MAP = "Quark:TreasureMap";
	public static String TAG_TREASURE_MAP_DELEGATE = "Quark:TreasureMapDelegate";

	ImmutableSet<ResourceLocation> tablesToEdit = ImmutableSet.of(LootTableList.CHESTS_DESERT_PYRAMID, LootTableList.CHESTS_JUNGLE_TEMPLE, LootTableList.CHESTS_STRONGHOLD_CORRIDOR);
	int rarity, quality;
	
	@Override
	public void setupConfig() {
		rarity = loadPropInt("Treasure map Rarity", "", 10);
		quality = loadPropInt("Treasure map item quality", "This is used for the luck attribute in loot tables. It doesn't affect the loot you get from the map itself.", 2);
	}
	
	@SubscribeEvent
	public void onLootTableLoad(LootTableLoadEvent event) {
		if(tablesToEdit.contains(event.getName()))
			event.getTable().getPool("main").addEntry(new LootEntryItem(Items.FILLED_MAP, rarity, quality, new LootFunction[] { new SetAsTreasureFunction() }, new LootCondition[0], "quark:treasure_map"));
	}

	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event) {
		if(event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
				ItemStack stack = player.inventory.getStackInSlot(i);
				if(stack != null && stack.hasTagCompound()) {
					if(ItemNBTHelper.getBoolean(stack, TAG_TREASURE_MAP_DELEGATE, false))
						makeMap(stack, player.worldObj, player.getPosition());
					
					if(ItemNBTHelper.getBoolean(stack, TAG_TREASURE_MAP, false)) {
						MapData data = (MapData) player.worldObj.loadItemData(MapData.class, "map_" + stack.getItemDamage());
						if(data != null) {
							int w = 128;
							byte[] colors = data.colors;

							int x = w / 2;
							int y = w / 2;
							byte color = (byte) 18;

							colors[xy(x, y)] = color;

							colors[xy(x - 1, y - 1)] = color;
							colors[xy(x - 2, y - 2)] = color;
							colors[xy(x + 1, y + 1)] = color;
							colors[xy(x + 2, y + 2)] = color;

							colors[xy(x + 1, y - 1)] = color;
							colors[xy(x + 2, y - 2)] = color;
							colors[xy(x - 1, y + 1)] = color;
							colors[xy(x - 2, y + 2)] = color;
						}
					}
				}
			}
		}
	}

	public ItemStack makeMap(ItemStack itemstack, World world, BlockPos sourcePos) {
		Random r = world.rand;

		BlockPos treasurePos;
		boolean validPos = false;
		int tries = 0;

		do {
			if(tries > 100)
				return null;

			int distance = 400 + r.nextInt(200);		
			double angle = r.nextFloat() * (Math.PI * 2);
			int x = (int) (sourcePos.getX() + Math.cos(angle) * distance);
			int z = (int) (sourcePos.getZ() + Math.sin(angle) * distance);
			treasurePos = world.getTopSolidOrLiquidBlock(new BlockPos(x, 255, z)).add(0, -4, 0);
			IBlockState state = world.getBlockState(treasurePos);
			if(state.getBlock() == Blocks.DIRT)
				validPos = true;
			tries++;
		} while(!validPos);

		String s = "map_" + itemstack.getMetadata();
		MapData mapdata = new MapData(s);
		world.setItemData(s, mapdata);
		mapdata.scale = 1;
		mapdata.xCenter = treasurePos.getX();
		mapdata.zCenter = treasurePos.getZ();
		mapdata.dimension = 0;
		mapdata.trackingPosition = true;
		mapdata.markDirty();

		world.setBlockState(treasurePos, Blocks.CHEST.getDefaultState());
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(treasurePos);
		
		chest.setLootTable(LootTableList.CHESTS_SIMPLE_DUNGEON, r.nextLong());

		ItemNBTHelper.setBoolean(itemstack, TAG_TREASURE_MAP, true);
		ItemNBTHelper.setBoolean(itemstack, TAG_TREASURE_MAP_DELEGATE, false);

		return itemstack;
	}

	public int xy(int x, int y) {
		return x + y * 128;
	}

	@Override
	public boolean hasSubscriptions() {
		return true;
	}
	
	public static class SetAsTreasureFunction extends LootFunction {

		protected SetAsTreasureFunction() {
			super(new LootCondition[0]);
		}

		@Override
		public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
			ItemNBTHelper.setBoolean(stack, TAG_TREASURE_MAP_DELEGATE, true);
			return stack;
		}
		
	}

}
