/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [27/03/2016, 06:02:47 (GMT)]
 */
package vazkii.quark.tweaks.feature;

import com.jcraft.jorbis.Block;

import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.quark.base.module.Feature;

public class NoteBlocksMobSounds extends Feature {

	@SubscribeEvent
	public void noteBlockPlayed(NoteBlockEvent.Play event) {
		BlockPos pos = event.getPos();
		
		BlockPos[] adj = new BlockPos[] {
				pos.north(),
				pos.south(),
				pos.east(),
				pos.west()
		};
		
		TileEntity tile = null;
		boolean can = false;
		for(BlockPos apos : adj) {
			tile = event.getWorld().getTileEntity(apos);
			if(tile != null && tile instanceof TileEntitySkull) {
				can = true;
				break;
			}
		}
		
		if(can) {
			int type = ((TileEntitySkull) tile).getSkullType(); 
			if(type != 3) {
				event.setCanceled(true);
				
				SoundEvent sound = null;
				switch(type) {
				case 0: 
				case 1:
					sound = SoundEvents.entity_skeleton_ambient;
					break;
				case 2:
					sound = SoundEvents.entity_zombie_ambient;
					break;
				case 4:
					sound = SoundEvents.entity_creeper_primed;
					break;
				case 5:
					sound = SoundEvents.entity_enderdragon_ambient;
					break;
				}
				
				if(sound != null) {
					System.out.println("play");
					event.getWorld().playSound(null, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, sound, SoundCategory.BLOCKS, 1F, 1F);
				}
			}
		}
	}
	
	@Override
	public boolean hasSubscriptions() {
		return true;
	}
	
}
