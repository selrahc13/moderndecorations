package com.selrahc13.moderndecorations.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public class DrawingHelper {
	public DrawingHelper() {
		
	}
	
	@SideOnly(Side.CLIENT)
	public void drawCube(Tessellator t, double x1, double y1, double z1, double x2, double y2, double z2, IIcon[] textures) {		
		/* Render Order
		 * CB
		 * DA
		 */
		
		// face south
    	float u = textures[0].getMinU();
    	float v = textures[0].getMinV();
    	float U = textures[0].getMaxU();
    	float V = textures[0].getMaxV();
		t.addVertexWithUV(x2, y1, z1, u, v);
		t.addVertexWithUV(x2, y2, z1, u, V);
		t.addVertexWithUV(x1, y2, z1, U, V);
		t.addVertexWithUV(x1, y1, z1, U, v);
		
		// face north
    	u = textures[1].getMinU();
    	v = textures[1].getMinV();
    	U = textures[1].getMaxU();
    	V = textures[1].getMaxV();
		t.addVertex(x1, y1, z2);
		t.addVertex(x1, y2, z2);
		t.addVertex(x2, y2, z2);
		t.addVertex(x2, y1, z2);
		
		// face west
    	u = textures[2].getMinU();
    	v = textures[2].getMinV();
    	U = textures[2].getMaxU();
    	V = textures[2].getMaxV();
		t.addVertex(x1, y1, z2);
		t.addVertex(x1, y2, z2);
		t.addVertex(x1, y2, z1);
		t.addVertex(x1, y1, z1);

		// face east
    	u = textures[3].getMinU();
    	v = textures[3].getMinV();
    	U = textures[3].getMaxU();
    	V = textures[3].getMaxV();
		t.addVertex(x2, y1, z1);
		t.addVertex(x2, y2, z1);
		t.addVertex(x2, y2, z2);
		t.addVertex(x2, y1, z2);

		// face top
    	u = textures[4].getMinU();
    	v = textures[4].getMinV();
    	U = textures[4].getMaxU();
    	V = textures[4].getMaxV();
		t.addVertex(x2, y2, z1);
		t.addVertex(x1, y2, z1);
		t.addVertex(x1, y2, z2);
		t.addVertex(x2, y2, z2);

		// face bottom
    	u = textures[5].getMinU();
    	v = textures[5].getMinV();
    	U = textures[5].getMaxU();
    	V = textures[5].getMaxV();
		t.addVertex(x1, y1, z1);
		t.addVertex(x2, y1, z1);
		t.addVertex(x2, y1, z2);
		t.addVertex(x1, y1, z2);
	}
}
