/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 *
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 *
 * File Created @ [05/04/2016, 22:55:30 (GMT)]
 */
package vazkii.quark.vanity.feature;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.quark.base.module.Feature;

public class SitInStairs extends Feature {

	@SubscribeEvent
	public void onInteract(PlayerInteractEvent.RightClickBlock event) {
		EntityPlayer player = event.getEntityPlayer();
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		IBlockState state = world.getBlockState(pos);

		ItemStack stack1 = player.getHeldItemMainhand();
		ItemStack stack2 = player.getHeldItemOffhand();
		if(stack1 != null || stack2 != null)
			return;

		if(state.getBlock() instanceof BlockStairs && state.getValue(BlockStairs.HALF) == EnumHalf.BOTTOM && !state.getBlock().isSideSolid(state, world, pos, event.getFace()) && canBeAbove(world, pos)) {
			List<Seat> seats = world.getEntitiesWithinAABB(Seat.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)));

			if(seats.isEmpty()) {
				Seat seat = new Seat(world, pos);
				world.spawnEntityInWorld(seat);
				event.getEntityPlayer().startRiding(seat);
			}
		}
	}

	@Override
	public boolean hasSubscriptions() {
		return true;
	}

	public static boolean canBeAbove(World world, BlockPos pos) {
		BlockPos upPos = pos.up();
		IBlockState state = world.getBlockState(upPos);
		Block block = state.getBlock();
		return block.getCollisionBoundingBox(state, world, upPos) == null;
	}

	public static class Seat extends Entity {

		public Seat(World world, BlockPos pos) {
			this(world);

			setPosition(pos.getX() + 0.5, pos.getY() + 0.25, pos.getZ() + 0.5);
		}

		public Seat(World par1World) {
			super(par1World);

			setSize(0F, 0F);
		}

		@Override
		public void onUpdate() {
			super.onUpdate();

			BlockPos pos = getPosition();
			if(pos != null && !(worldObj.getBlockState(pos).getBlock() instanceof BlockStairs) || !canBeAbove(worldObj, pos)) {
				setDead();
				return;
			}

			List<Entity> passangers = getPassengers();
			if(passangers.isEmpty())
				setDead();
			for(Entity e : passangers)
				if(e.isSneaking())
					setDead();
		}

		@Override protected void entityInit() { }
		@Override protected void readEntityFromNBT(NBTTagCompound nbttagcompound) { }
		@Override protected void writeEntityToNBT(NBTTagCompound nbttagcompound) { }
	}

}
