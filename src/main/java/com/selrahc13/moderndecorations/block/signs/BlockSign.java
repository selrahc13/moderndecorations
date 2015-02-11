package com.selrahc13.moderndecorations.block.signs;

import com.carpentersblocks.api.ICarpentersHammer;
import com.selrahc13.moderndecorations.block.enums.BlockEnum;
import com.selrahc13.moderndecorations.common.ModernDecorations;
import com.selrahc13.moderndecorations.common.Reference;
import com.selrahc13.moderndecorations.tileentity.TileEntitySign;
import com.selrahc13.moderndecorations.util.BlockHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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

public class BlockSign extends BlockContainer {

	private String iconName;
	private int blockType = 0;
	// How far above and below do we search for other signposts to change the post type
	private int searchRadius = 3;
	
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

		// Search above and below for signposts and match their texture to the bottom
		TileEntitySign base = null;
		String pType = "";
		int direction = 0;
		int srMin = y-searchRadius;
		int srMax = y+searchRadius;
		for (int sY = srMin; sY < srMax; sY++) {
			base = (TileEntitySign) world.getTileEntity(x, sY, z);
			if (base != null) {
				if (sY == srMin) { srMin--; sY=sY-2; continue; } // Found sign at the bottom, search lower
				if (sY == srMax) srMax++; // We found a sign post, so keep searching higher
				if (pType == "") {
			    	direction = world.getBlockMetadata(base.xCoord, base.yCoord, base.zCoord);
					pType = base.postType;
				} else {
					base.setPostType(pType);
					world.setBlockMetadataWithNotify(base.xCoord, base.yCoord, base.zCoord, direction, 0);
					world.markBlockForUpdate(x, sY, z);
					srMax++;
				}
			}
		}
		
		if (tileEntity != null) {
			if (itemStack.stackTagCompound != null) {
				if (itemStack.stackTagCompound.hasKey("SignType")) {
					tileEntity.setSignType(itemStack.stackTagCompound.getInteger("SignType"));
				} else {
					tileEntity.setSignType(0);
				}
			}
			if (pType != "") {
				tileEntity.setPostType(pType);
				world.setBlockMetadataWithNotify(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, direction, 0);
				//world.markBlockForUpdate(x, y, z);
			}
		}
		
	}

	@Override
    public boolean onBlockActivated(World theWorld, int posX, int posY, int posZ, EntityPlayer thePlayer, int side, float hitX, float hitY, float hitZ)
    {
    	TileEntity te = theWorld.getTileEntity(posX, posY, posZ);
    	String pType = "";
    	if (!theWorld.isRemote && te != null && te instanceof TileEntitySign) {
    		if(thePlayer.getHeldItem() != null && thePlayer.getHeldItem().getUnlocalizedName().substring(5).equals("stick")) {
	    		TileEntitySign sign = (TileEntitySign)te;
	    		int listlen = BlockEnum.EnumPosts.values().length;
	    		int index = BlockEnum.EnumPosts.valueOf(sign.postType).ordinal();
	    		if (index < listlen - 1) {
	    			index++;
	    		} else {
	    			index=0;
	    		}
	    		theWorld.markBlockForUpdate(posX, posY, posZ);
	    		
	    		// Search above and below for signposts and match their texture to the bottom
	    		TileEntitySign base = null;
	    		pType = BlockEnum.EnumPosts.values()[index].name();
	    		boolean found = false;
	    		int srMin = posY-searchRadius;
	    		int srMax = posY+searchRadius;
	    		int direction = 0;
	    		for (int sY = srMin; sY <= srMax; sY++) {
	    			base = (TileEntitySign) theWorld.getTileEntity(posX, sY, posZ);
	    			if (base != null) {
    					base.setPostType(pType);
    					if (sY == srMin) { srMin--; sY=sY-2; continue; } // Found sign at the bottom, search lower
    					if (sY == srMax) srMax++; // We found a sign post, so keep searching higher
	    				if (!found) {
	    					found = true;
	    					direction = theWorld.getBlockMetadata(base.xCoord, base.yCoord, base.zCoord);	    				
	    				} else {
	    					theWorld.setBlockMetadataWithNotify(base.xCoord, base.yCoord, base.zCoord, direction, 0);	    					
	    				}
    					theWorld.markBlockForUpdate(posX, sY, posZ);
	    			}
	    		}

	    	} else {
	    		// if the player isn't holding the post-changing item
	    		return false;
	    	}
	    	// player is holding post-changing item
    		theWorld.playSoundAtEntity(thePlayer, pType.contains("wood") ? "random.wood_click" : "random.anvil_land", 0.6f, 1.0f);
	        return true;
    	}
    	// we only run changer code client-side
    	return false;
    }
	
    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer entityPlayer)
    {
    	ModernDecorations.logger.info("onBlockClicked called");
    
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
