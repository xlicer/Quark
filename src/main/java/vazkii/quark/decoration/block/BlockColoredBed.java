/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [03/07/2016, 21:47:19 (GMT)]
 */
package vazkii.quark.decoration.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.quark.base.block.IBlockColorProvider;
import vazkii.quark.base.block.IQuarkBlock;
import vazkii.quark.base.item.IItemColorProvider;
import vazkii.quark.base.item.ItemModBlock;
import vazkii.quark.base.lib.LibMisc;
import vazkii.quark.decoration.feature.ColoredBeds;

public class BlockColoredBed extends BlockBed implements IQuarkBlock, IBlockColorProvider {

	private final String[] variants;
	private final String bareName;
	int color;

	public BlockColoredBed(String name, int color) {
		variants = new String[] { name };
		bareName = name;
		this.color = color;

		setSoundType(SoundType.WOOD);
		setHardness(0.2F);
		setUnlocalizedName(name);
	}

	@Override
	public Block setUnlocalizedName(String name) {
		super.setUnlocalizedName(name);
		setRegistryName(LibMisc.PREFIX_MOD + name);
		GameRegistry.register(this);
		GameRegistry.register(new ItemModBlock(this), new ResourceLocation(LibMisc.PREFIX_MOD + name));
		return this;
	}
	
	@Override
	public boolean isBed(IBlockState state, IBlockAccess world, BlockPos pos, Entity player) {
		return true;
	}
	
	@Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return state.getValue(PART) == BlockBed.EnumPartType.HEAD ? null : ColoredBeds.colored_bed_item;
    }
    
	@Override
	public int damageDropped(IBlockState state) {
		return color;
	}
	
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ColoredBeds.colored_bed_item, 1, color);
    }

	@Override
	public String getBareName() {
		return bareName;
	}

	@Override
	public String[] getVariants() {
		return variants;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemMeshDefinition getCustomMeshDefinition() {
		return null;
	}

	@Override
	public EnumRarity getBlockRarity(ItemStack stack) {
		return EnumRarity.COMMON;
	}

	@Override
	public IProperty[] getIgnoredProperties() {
		return new IProperty[] { OCCUPIED };
	}

	@Override
	public IProperty getVariantProp() {
		return null;
	}

	@Override
	public Class getVariantEnum() {
		return null;
	}

	@Override
	public IStateMapper getStateMapper() {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IItemColor getItemColor() {
		return ((IItemColorProvider) ColoredBeds.colored_bed_item).getItemColor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IBlockColor getBlockColor() {
		return new IBlockColor() {
			
			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
				return ColoredBeds.getColor(color);
			}
		};
	}

}
