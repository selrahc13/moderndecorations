package com.selrahc13.moderndecorations.common;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import org.apache.logging.log4j.Logger;

import com.selrahc13.moderndecorations.block.enums.BlockEnum;
import com.selrahc13.moderndecorations.block.signs.BlockSign;
import com.selrahc13.moderndecorations.block.signs.BlockSignISBRH;
import com.selrahc13.moderndecorations.tileentity.TileEntitySign;
import com.selrahc13.moderndecorations.util.BlockHelper;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(
		modid=Reference.MODID, 
		name=Reference.MODNAME, 
		version=Reference.MODVERSION,
		dependencies = "required-after:Forge@[10.13.0.1230,)"
	)
public class ModernDecorations {
	@Instance(Reference.MODID)
	public static ModernDecorations instance;	
	
	public static Logger logger;
	
	public static BlockHelper blockHelper = new BlockHelper();

	public static int ticker = 0;
	public static ArrayList<BlockSignISBRH> blockSigns = new ArrayList<BlockSignISBRH>();
	
	public static CreativeTabs tabCreative = new CreativeTabs(Reference.MODID + "_tab" + Reference.MODID) {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.bookshelf);
		}
	};
	
	@SidedProxy(serverSide="com.selrahc13.moderndecorations.common.CommonProxy", clientSide="com.selrahc13.moderndecorations.client.ClientProxy") 
	public static CommonProxy proxy; 

	@EventHandler	// used in 1.6.2
	//@PreInit		// used in 1.5.2
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		// Register new materials
				
		// Register new items	
		logger.info("Registering items");
		RegisterBlocks();
	}

	@EventHandler	// used in 1.6.2
	//@Init			// used in 1.5.2
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
	}
	
	@EventHandler	// used in 1.6.2
	//@PostInit		// used in 1.5.2
	public void postInit(FMLPostInitializationEvent event) {
		// Stub method
	}	
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
	}
	
	public static void RegisterBlocks() {
		for (int i = 0; i < BlockEnum.EnumSigns.values().length; i++) {
			Block bk = new BlockSign(i);
			String name = BlockHelper.typeToNameMapping.get(i);
			ModernDecorations.logger.info("Registering block: " + name);
			GameRegistry.registerBlock(bk, name);
			GameRegistry.registerTileEntity(TileEntitySign.class, name);
		}
		//BlockSignISBRH bk = new BlockSignISBRH(Material.iron);
		//blockSigns.add(bk);
		//GameRegistry.registerBlock((Block)bk, bk.getUnlocalizedName());
		//GameRegistry.registerTileEntity(TileEntityTrafficLightEntity.class, "tileEntityTrafficLight");
	}
	
}

