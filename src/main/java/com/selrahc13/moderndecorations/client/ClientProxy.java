package com.selrahc13.moderndecorations.client;

import com.selrahc13.moderndecorations.common.CommonProxy;
import com.selrahc13.moderndecorations.common.ModernDecorations;
import com.selrahc13.moderndecorations.renderer.tileentity.TileEntityRendererSign;
import com.selrahc13.moderndecorations.tileentity.TileEntitySign;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		// This is for rendering entities etc
		ModernDecorations.logger.info("Registering renderers");
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySign.class, new TileEntityRendererSign());
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrafficLightEntity.class, new TileEntityTrafficLightRenderer());
		
		/*
		for (BlockSignISBRH bk : ModernDecorations.blockSigns) {
			ISimpleBlockRenderingHandler myISBRH = (ISimpleBlockRenderingHandler) new SignRenderer();
			RenderingRegistry.registerBlockHandler(bk.getRenderType(), myISBRH);
		}*/

	}
	
}
