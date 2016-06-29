/**
 * This class was created by <SanAndreasP>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 *
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 *
 * File Created @ [20/03/2016, 22:33:44 (GMT)]
 */
package vazkii.quark.decoration.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.quark.base.block.IQuarkBlock;
import vazkii.quark.base.item.IExtraVariantHolder;
import vazkii.quark.base.item.ItemModBlock;
import vazkii.quark.decoration.block.BlockCustomChest;
import vazkii.quark.decoration.feature.VariedChests;
import vazkii.quark.decoration.feature.VariedChests.ChestType;
import vazkii.quark.decoration.tile.TileCustomChest;

public class ItemChestBlock extends ItemModBlock {

    public ItemChestBlock(Block block) {
        super(block);
    }

    @Override
    public int getMetadata(int damage) {
        return 0;
    }
    
    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {
    	return new ItemMeshDefinition() {
			
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				ChestType type = VariedChests.custom_chest.getCustomType(stack);
				return type.normalModel;
			}
		};
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
        int typeCnt = 0;

        BlockPos posN = pos.north();
        BlockPos posS = pos.south();
        BlockPos posW = pos.west();
        BlockPos posE = pos.east();

        VariedChests.ChestType myType = VariedChests.custom_chest.getCustomType(stack);

        if(world.getBlockState(posN).getBlock() == this.block && VariedChests.custom_chest.getCustomType(world, posN) == myType)
            typeCnt += VariedChests.custom_chest.isDoubleChest(world, posN, myType) ? 2 : 1;
        if(world.getBlockState(posS).getBlock() == this.block && VariedChests.custom_chest.getCustomType(world, posS) == myType)
            typeCnt += VariedChests.custom_chest.isDoubleChest(world, posS, myType) ? 2 : 1;
        if(world.getBlockState(posW).getBlock() == this.block && VariedChests.custom_chest.getCustomType(world, posW) == myType)
            typeCnt += VariedChests.custom_chest.isDoubleChest(world, posW, myType) ? 2 : 1;
        if(world.getBlockState(posE).getBlock() == this.block && VariedChests.custom_chest.getCustomType(world, posE) == myType)
            typeCnt += VariedChests.custom_chest.isDoubleChest(world, posE, myType) ? 2 : 1;

        if(typeCnt <= 1 && super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState)) {
            TileEntity te = world.getTileEntity(pos);
            if(te instanceof TileCustomChest) {
                ((TileCustomChest) te).chestType = myType;
                return true;
            }
        }

        return false;
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
    	BlockCustomChest chest = (BlockCustomChest) Block.getBlockFromItem(itemIn);
        for(VariedChests.ChestType type : VariedChests.ChestType.VALID_TYPES)
            subItems.add(chest.setCustomType(new ItemStack(itemIn, 1), type));
    }

}
