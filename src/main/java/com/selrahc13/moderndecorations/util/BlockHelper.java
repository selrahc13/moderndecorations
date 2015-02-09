package com.selrahc13.moderndecorations.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;

import com.selrahc13.moderndecorations.block.enums.BlockEnum;
import com.selrahc13.moderndecorations.common.Reference;

public class BlockHelper {
	public static Map<Integer, String> typeToNameMapping = new HashMap<Integer, String>();
	public static Map<Integer, String> typeToTextureNameMapping = new HashMap<Integer, String>();
	public static Map<String, ResourceLocation> nameToResourceLocation = new HashMap<String, ResourceLocation>();
	public static Map<String, ResourceLocation> nameToPostResourceLocation = new HashMap<String, ResourceLocation>();
	
	public BlockHelper() {		
		for (BlockEnum.EnumSigns block : BlockEnum.EnumSigns.values()) {
			typeToNameMapping.put(block.ordinal(), block.name());
			typeToTextureNameMapping.put(block.ordinal(), block.name());
			nameToResourceLocation.put(block.name(), new ResourceLocation(Reference.MODID.toLowerCase(), "textures/signs/" + block.name() + ".png"));
		}
		
		for (BlockEnum.EnumPosts block : BlockEnum.EnumPosts.values()) {
			nameToPostResourceLocation.put(block.name(), new ResourceLocation(Reference.MODID.toLowerCase(), "textures/posts/" + block.name() + ".png"));
		}
	}

}
