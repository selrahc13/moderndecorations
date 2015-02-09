package com.selrahc13.moderndecorations.block.signs;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.carpentersblocks.api.ICarpentersHammer;
import com.selrahc13.moderndecorations.common.ModernDecorations;
import com.selrahc13.moderndecorations.common.Reference;
import com.selrahc13.moderndecorations.tileentity.TileEntitySign;
import com.selrahc13.moderndecorations.util.BlockHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSign extends BlockContainer implements ICarpentersHammer {

	private String iconName;
	private int blockType = 0;
	
	public BlockSign(int type) {
		super(Material.iron);

		iconName = BlockHelper.typeToTextureNameMapping.get(type);

		this.blockType = type;
		this.setCreativeTab(ModernDecorations.tabCreative);
		this.setHardness(2.0f);
		this.setBlockBounds(0.34F, 0.0F, 0.34F, 0.66F, 1.0f, 0.66F);
		this.setBlockName(iconName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(Reference.MODID.toLowerCase()+":"+this.iconName.replace("mdWide", "md"));
	}

	@Override
	public int getRenderType()
	{
		// don't render this
		return -1;
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
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySign(this.blockType);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, l, 2);
		TileEntitySign tileEntity = (TileEntitySign) world.getTileEntity(x, y, z);
	
		if (tileEntity != null) {
			if (itemStack.stackTagCompound != null) {
				if (itemStack.stackTagCompound.hasKey("SignType")) {
					tileEntity.setSignType(itemStack.stackTagCompound.getInteger("SignType"));
				} else {
					tileEntity.setSignType(0);
				}

			}
		}
	}

	@Override
	public void onHammerUse(World world, EntityPlayer player) {
		
	}

	@Override
	public boolean canUseHammer(World world, EntityPlayer player) {
		return true;
	}

	
    @Override
    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer entityPlayer)
    {
        if (world.isRemote) {
            return;
        }

        ItemStack itemStack = entityPlayer.getCurrentEquippedItem();

        if (itemStack == null) {
            return;
        }
        Item item = itemStack.getItem();

        if (item instanceof ICarpentersHammer && ((ICarpentersHammer)item).canUseHammer(world, entityPlayer)) {
        }
    }	
}
