package com.selrahc13.moderndecorations.block.signs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import com.selrahc13.moderndecorations.block.enums.BlockEnum;
import com.selrahc13.moderndecorations.common.ModernDecorations;
import com.selrahc13.moderndecorations.util.BlockHelper;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSignISBRH extends Block {

	private String textureName;
	private int blockType = 0;
	private int renderID;
	public IIcon[] icons = new IIcon[6];
	
	public BlockSignISBRH(Material mat) {
		super(Material.iron);

		textureName = BlockHelper.typeToTextureNameMapping.get(BlockEnum.EnumSigns.mdStopSign.ordinal());

		this.renderID = RenderingRegistry.getNextAvailableRenderId();
		this.setBlockType(BlockEnum.EnumSigns.mdStopSign.ordinal());
		this.setCreativeTab(ModernDecorations.tabCreative);
		this.setHardness(2.0f);
		this.setBlockBounds(0.34F, 0.0F, 0.34F, 0.66F, 1.0f, 0.66F);
		this.setBlockName(textureName + "_ISBRH");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.icons[0] = reg.registerIcon(textureName);
		this.icons[1] = reg.registerIcon(BlockHelper.typeToTextureNameMapping.get(BlockEnum.EnumSigns.mdSignPost.ordinal()));
		this.icons[2] = reg.registerIcon(BlockHelper.typeToTextureNameMapping.get(BlockEnum.EnumSigns.mdYieldSign.ordinal()));
		this.icons[3] = reg.registerIcon(BlockHelper.typeToTextureNameMapping.get(BlockEnum.EnumSigns.md2hrParkingLimit.ordinal()));
		this.icons[4] = reg.registerIcon(BlockHelper.typeToTextureNameMapping.get(BlockEnum.EnumSigns.mdCrosswalkInstructions.ordinal()));
		this.icons[5] = reg.registerIcon(BlockHelper.typeToTextureNameMapping.get(BlockEnum.EnumSigns.mdDeerCrossing.ordinal()));
	}
	
	@Override
	public int getRenderType()
	{
		return renderID;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean hasTileEntity()
	{
		return false;
	}
	
	public IIcon getIcon(int side, int meta) {
	    return this.icons[side];
	}
	
	public IIcon[] getIcons() {
		return this.icons;
	}

	/**
	 * @return the blockType
	 */
	public int getBlockType() {
		return blockType;
	}

	/**
	 * @param blockType the blockType to set
	 */
	public void setBlockType(int blockType) {
		this.blockType = blockType;
	}
}
