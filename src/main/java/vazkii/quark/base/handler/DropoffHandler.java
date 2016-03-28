/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [28/03/2016, 16:47:58 (GMT)]
 */
package vazkii.quark.base.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.InvWrapper;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.tweaks.feature.StoreToChests;

public final class DropoffHandler {

	public static void dropoff(EntityPlayer player, boolean smart) {
		if (!ModuleLoader.isFeatureEnabled(StoreToChests.class))
			return;

		new Dropoff(player, smart).execute();
	}
	
    public static IItemHandler getInventory(EntityPlayer player, World world, BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);
        
        if(te == null)
            return null;
        
        boolean accept = te instanceof IDropoffManager && ((IDropoffManager) te).acceptsDropoff();
        if(!accept)
        	accept = te instanceof TileEntityChest || te.getClass().getSimpleName().toLowerCase().contains("chest");
        
        IItemHandler ret = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        
        if(te instanceof TileEntityChest) {
            Block chestBlock = world.getBlockState(pos).getBlock();
            if(world.getBlockState(pos.west()).getBlock() == chestBlock)
                ret = new InvWrapper(new InventoryLargeChest("Large chest", (ILockableContainer) world.getTileEntity(pos.west()), (ILockableContainer) te));
            if(world.getBlockState(pos.east()).getBlock() == chestBlock)
                ret = new InvWrapper(new InventoryLargeChest("Large chest", (ILockableContainer) te, (ILockableContainer) world.getTileEntity(pos.east())));
            if(world.getBlockState(pos.north()).getBlock() == chestBlock)
                ret = new InvWrapper(new InventoryLargeChest("Large chest", (ILockableContainer) world.getTileEntity(pos.north()), (ILockableContainer) te));
            if(world.getBlockState(pos.south()).getBlock() == chestBlock)
                ret = new InvWrapper(new InventoryLargeChest("Large chest", (ILockableContainer) te, (ILockableContainer) world.getTileEntity(pos.south())));
        }
        
        if(te instanceof IInventory) {
        	accept &= ((IInventory) te).isUseableByPlayer(player);
        	
        	if(accept && ret == null) 
        		ret = new InvWrapper((IInventory) te);
        }
        
        return accept ? ret : null;
    }

	public static class Dropoff {

		final EntityPlayer player;
		final boolean smart;

		List<Pair<IItemHandler, Double>> itemHandlers = new ArrayList();

		public Dropoff(EntityPlayer player, boolean smart) {
			this.player = player;
			this.smart = smart;
		}

		public void execute() {
			locateItemHandlers();
			
			if(itemHandlers.isEmpty())
				return;
			
			if(smart)
				smartDropoff();
			else roughDropoff();
		}

		public void smartDropoff() {
			dropoff((stack, handler) -> {
				int slots = handler.getSlots();
				for(int i = 0; i < slots; i++) {
					ItemStack stackAt = handler.getStackInSlot(i);
					if(stackAt == null)
						continue;
					
					boolean itemEqual = stack.getItem() == stackAt.getItem();
					boolean damageEqual = stack.getItemDamage() == stackAt.getItemDamage();
					boolean nbtEqual = ItemStack.areItemStackTagsEqual(stackAt, stack);

					if(itemEqual && damageEqual && nbtEqual)
						return true;

					if(!stack.getHasSubtypes() && stack.isItemStackDamageable() && stack.getMaxStackSize() == 1 && itemEqual && nbtEqual)
						return true;
				}
					
				return false;
			});
		}
			
		public void roughDropoff() {
			dropoff((stack, handler) -> true);
		}

		public void locateItemHandlers() {
			BlockPos playerPos = player.getPosition();
			int range = 6;
			
			for(int i = -range; i < range * 2 + 1; i++)
				for(int j = -range; j < range * 2 + 1; j++)
					for(int k = -range; k < range * 2 + 1; k++) {
						BlockPos pos = playerPos.add(i, j, k);
						IItemHandler handler = getInventory(player, player.worldObj, pos);
						if(handler != null)
							itemHandlers.add(Pair.of(handler, player.getDistanceSq(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5)));
					}
			
			Collections.sort(itemHandlers, (pair1, pair2) -> Double.compare(pair1.getRight(), pair2.getRight()));
		}
		
		public void dropoff(DropoffPredicate pred) {
			InventoryPlayer inv = player.inventory;
			
			for(int i = InventoryPlayer.getHotbarSize(); i < inv.mainInventory.length; i++) {
				ItemStack stackAt = inv.getStackInSlot(i);
				if(stackAt != null) {
					ItemStack ret = insert(stackAt, pred);
					if(!ItemStack.areItemsEqual(stackAt, ret))
						inv.setInventorySlotContents(i, ret);
				}
			}
		}
		
		public ItemStack insert(ItemStack stack, DropoffPredicate pred) {
			ItemStack ret = stack.copy();
			for(Pair<IItemHandler, Double> pair : itemHandlers) {
				IItemHandler handler = pair.getLeft();
				if(pred.apply(ret, handler)) {
					ret = ItemHandlerHelper.insertItemStacked(handler, ret, false);
					if(ret != null)
						ret = ret.copy();
					
					if(ret == null || ret.stackSize == 0)
						return null;
				}
			}
			
			return ret;
		}
	}
	
	public static interface DropoffPredicate {
		
		public boolean apply(ItemStack stack, IItemHandler handler);
		
	}
	
}
