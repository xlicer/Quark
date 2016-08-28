/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 * 
 * File Created @ [28/08/2016, 00:46:22 (GMT)]
 */
package vazkii.quark.base.block;

import net.minecraft.block.material.Material;
import vazkii.arl.block.BlockModSlab;

public class BlockQuarkSlab extends BlockModSlab implements IQuarkBlock {

	public BlockQuarkSlab(String name, Material materialIn, boolean doubleSlab) {
		super(name, materialIn, doubleSlab);
	}

}
