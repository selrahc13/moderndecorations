package com.selrahc13.moderndecorations.renderer.isbrh;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.selrahc13.moderndecorations.block.signs.BlockSignISBRH;
import com.selrahc13.moderndecorations.util.DrawingHelper;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SignRenderer implements ISimpleBlockRenderingHandler {

	private static DrawingHelper drawingHelper = new DrawingHelper();
	public int renderID;
	
	public SignRenderer() {
		this.renderID = RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unused")
	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {

		BlockSignISBRH bk = (BlockSignISBRH) block;
		int meta = world.getBlockMetadata(x, y, z);
		int lightValue = block.getMixedBrightnessForBlock(world, x, y, z);
		
		IIcon c = block.getIcon(0, meta);
    	IIcon b = block.getIcon(1, meta);
    	IIcon[] textures = bk.getIcons();
    	    	
    	float u = c.getMinU();
    	float v = c.getMinV();
    	float U = c.getMaxU();
    	float V = c.getMaxV();
    	
    	float u1 = b.getMinU();
    	float v1 = b.getMinV();
    	float U1 = b.getMaxU();
    	float V1 = b.getMaxV();
    
		Tessellator tess = Tessellator.instance;
		tess.addTranslation(x, y, z);
		// TODO Sign rendering code goes here
		tess.setBrightness(lightValue);
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		drawingHelper.drawCube(tess, 0, 0, 0, 1, 1, 2, textures);
		tess.addTranslation(-x,  -y, -z);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return renderID;
	}

}
