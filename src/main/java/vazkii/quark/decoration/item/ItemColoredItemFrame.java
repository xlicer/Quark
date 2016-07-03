/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [19/06/2016, 22:34:03 (GMT)]
 */
package vazkii.quark.decoration.item;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.quark.base.item.IItemColorProvider;
import vazkii.quark.base.item.IExtraVariantHolder;
import vazkii.quark.base.item.ItemMod;
import vazkii.quark.decoration.entity.EntityColoredItemFrame;

public class ItemColoredItemFrame extends ItemMod implements IItemColorProvider, IExtraVariantHolder {

	private static final String[] VARIANTS = {
			"colored_item_frame_white",
			"colored_item_frame_orange",
			"colored_item_frame_magenta",
			"colored_item_frame_light_blue",
			"colored_item_frame_yellow",
			"colored_item_frame_lime",
			"colored_item_frame_pink",
			"colored_item_frame_gray",
			"colored_item_frame_silver",
			"colored_item_frame_cyan",
			"colored_item_frame_purple",
			"colored_item_frame_blue",
			"colored_item_frame_brown",
			"colored_item_frame_green",
			"colored_item_frame_red",
			"colored_item_frame_black"
	};

	private static final String[] EXTRA_VARIANTS = {
			"colored_item_frame_normal",
			"colored_item_frame_wood",
			"colored_item_frame_map",
			"colored_item_frame_map_wood"
	};
	
	public ItemColoredItemFrame() {
		super("colored_item_frame", VARIANTS);
		setCreativeTab(CreativeTabs.DECORATIONS);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		BlockPos blockpos = pos.offset(facing);

		if(facing != EnumFacing.DOWN && facing != EnumFacing.UP && playerIn.canPlayerEdit(blockpos, facing, stack)) {
			EntityHanging entityhanging = createEntity(worldIn, blockpos, facing, stack.getItemDamage());

			if(entityhanging != null && entityhanging.onValidSurface()) {
				if(!worldIn.isRemote) {
					entityhanging.playPlaceSound();
					worldIn.spawnEntityInWorld(entityhanging);
				}

				--stack.stackSize;
			}

			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.FAIL;
	}

	private EntityHanging createEntity(World worldIn, BlockPos pos, EnumFacing clickedSide, int color) {
		return new EntityColoredItemFrame(worldIn, pos, clickedSide, color);
	}

	@Override
	public String getUniqueModel() {
		return "colored_item_frame";
	}

	@Override
	public String[] getExtraVariants() {
		return EXTRA_VARIANTS;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IItemColor getItemColor() {
		return new IItemColor() {

			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return tintIndex == 1 ? ItemDye.DYE_COLORS[15 - Math.min(15, stack.getItemDamage())] : 0xFFFFFF;
			}
		};
	}

}
