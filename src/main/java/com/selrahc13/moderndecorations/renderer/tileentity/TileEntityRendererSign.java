package com.selrahc13.moderndecorations.renderer.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.selrahc13.moderndecorations.models.ModelDoublePost;
import com.selrahc13.moderndecorations.models.ModelPost;
import com.selrahc13.moderndecorations.models.ModelSign;
import com.selrahc13.moderndecorations.models.ModelWideSign;
import com.selrahc13.moderndecorations.tileentity.TileEntitySign;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class TileEntityRendererSign extends TileEntitySpecialRenderer {
	private static ModelSign signModel = new ModelSign();
	private static ModelPost postModel = new ModelPost();
	private static ModelWideSign wideSignModel = new ModelWideSign();
	private static ModelDoublePost doublePostModel = new ModelDoublePost();
	
	public int renderID;
	
	public TileEntityRendererSign() {
		this.renderID = RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x,
			double y, double z, float scale) {
		TileEntitySign sign = (TileEntitySign) te;
		// get orientation of TE
    	int direction = te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);
    	if (direction == 3) direction = 1;
    	else if (direction == 1) direction = 3;
    	else if (direction == 0) direction = 2;
    	else if (direction == 2) direction = 0;
    	
    	// start rendering code
		GL11.glPushMatrix();
		{
			//GL11.glScalef(0.1f, 0.1f, 0.1f);
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			
			Minecraft.getMinecraft().renderEngine.bindTexture(sign.postTexture);
			// So we don't render upside down
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
			// Rotate the model to face toward player when placed
			GL11.glRotatef(direction * 90.0F, 0.0F, 1.0F, 0.0F);
			
			if (sign.name.startsWith("mdWide")) {
				TileEntityRendererSign.doublePostModel.render(te, 0.625f);
				Minecraft.getMinecraft().renderEngine.bindTexture(sign.signTexture);
				TileEntityRendererSign.wideSignModel.render(te, 0.625f);
			} else {
				TileEntityRendererSign.postModel.render(te, 0.625f);
				Minecraft.getMinecraft().renderEngine.bindTexture(sign.signTexture);
				TileEntityRendererSign.signModel.render(te, 0.625f);
			}
			
		}
		GL11.glPopMatrix();
    }
}
