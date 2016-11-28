package vazkii.quark.automation.feature;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.quark.base.module.Feature;
import vazkii.quark.base.module.ModuleLoader;

public class PistonSpikes extends Feature {

	// Called from ASM. See ClassTransformer
	public static boolean breakStuffWithSpikes(World world, List<BlockPos> moveList, List<BlockPos> destroyList, EnumFacing facing, boolean extending) {
		if(!extending || !ModuleLoader.isFeatureEnabled(PistonSpikes.class))
			return false;
		
		boolean did = false;
		List<BlockPos> moveListCopy = new ArrayList(moveList);
		for(BlockPos pos : moveListCopy) {
			IBlockState state = world.getBlockState(pos);
			if(state.getBlock() == Blocks.END_ROD && state.getValue(BlockDirectional.FACING) == facing) {
				BlockPos off = pos.offset(facing);
				destroyList.add(off);
				if(moveList.contains(off))
					moveList.remove(off);
				
				for(int i = 0; i < 12; i++) {
					off = off.offset(facing);
					IBlockState stateAt = world.getBlockState(off);
					Block blockAt = stateAt.getBlock();
					if(blockAt.isAir(stateAt, world, off) || blockAt == Blocks.SLIME_BLOCK)
						break;
					
					if(moveList.contains(off))
						moveList.remove(off);
					else if(destroyList.contains(off))
						destroyList.remove(off);
				}
				
				did = true;
			}
		}
		
		return did;
	}
	
}
