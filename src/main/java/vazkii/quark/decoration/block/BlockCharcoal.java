/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [20/06/2016, 11:48:13 (GMT)]
 */
package vazkii.quark.decoration.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.quark.base.block.BlockMod;

public class BlockCharcoal extends BlockMod {

	public BlockCharcoal() {
		super("charcoal_block", Material.ROCK);
		setHardness(5.0F);
		setResistance(10.0F);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	@Override
	public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
		return true;
	}

}
