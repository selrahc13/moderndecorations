package com.selrahc13.moderndecorations.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import com.selrahc13.moderndecorations.block.enums.BlockEnum;
import com.selrahc13.moderndecorations.common.ModernDecorations;
import com.selrahc13.moderndecorations.util.BlockHelper;

public class TileEntitySign extends TileEntity {
	public ResourceLocation signTexture;
	public ResourceLocation postTexture;
	public String name;
	public int signType = 0;
	public String postType = "post_steel";
	
	public TileEntitySign(int type) {
		this.signType = type;
		this.name = BlockHelper.typeToNameMapping.get(type);
		this.signTexture = BlockHelper.nameToResourceLocation.get(name);
		this.postTexture = BlockHelper.nameToPostResourceLocation.get(postType);
		ModernDecorations.logger.info(this.name + " "+ this.signType);
	}

	public TileEntitySign() {
		this.setup();
	}
	
	public void setSignType(int type) {
		this.signType = type;
		this.setup();
	}
	
	public void setPostType(String type) {
		this.postType = type;
		this.postTexture = BlockHelper.nameToPostResourceLocation.get(postType);
	}
	
	public void setPostType(int index) {
		setPostType(BlockEnum.EnumPosts.values()[index].name());
	}
	
	private void setup() {
		this.name = BlockHelper.typeToNameMapping.get(signType);
		this.signTexture = BlockHelper.nameToResourceLocation.get(name);
		//FIXME: Next line is temporary because I made a typo
		postType = (postType.contains("steel_post")) ? "post_steel" : postType;
		this.postTexture = BlockHelper.nameToPostResourceLocation.get(postType);
	}
	
    protected void readType(NBTTagCompound nbtTag) {
        signType = nbtTag.getInteger("SignType");
        postType = nbtTag.getString("PostType");
        this.setup();
    }

    protected void saveType(NBTTagCompound nbtTag) {
        nbtTag.setInteger("SignType", signType);
        nbtTag.setString("PostType", postType);
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);

        this.signType = (nbtTag.hasKey("SignType")) ? nbtTag.getInteger("SignType") : 0;        
        this.postType = (nbtTag.hasKey("PostType")) ? nbtTag.getString("PostType") : "post_steel";
        
        this.setup();
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);

        nbtTag.setInteger("SignType", signType);
        nbtTag.setString("PostType", postType);
    }

    /**
     * Called when you receive a TileEntityData packet for the location this
     * TileEntity is currently in. On the client, the NetworkManager will always
     * be the remote server. On the server, it will be whomever is responsible for
     * sending the packet.
     *
     * @param net    The NetworkManager the packet originated from
     * @param packet The data packet
     */
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }
    
}
