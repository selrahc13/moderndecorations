package com.selrahc13.moderndecorations.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.selrahc13.moderndecorations.common.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterHelper {
	public static void RegisterBlock (Block block) {
		GameRegistry.registerBlock(block, Reference.MODID + "_" + block.getUnlocalizedName().substring(5));
	}

	public static void RegisterItem (Item item) {
		GameRegistry.registerItem(item, Reference.MODID + "_" + item.getUnlocalizedName().substring(5));
	}
	
	
}
