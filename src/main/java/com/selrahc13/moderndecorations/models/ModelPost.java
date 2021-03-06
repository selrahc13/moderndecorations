package com.selrahc13.moderndecorations.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class ModelPost extends ModelBase
{
	ModelRenderer Post;
	public ModelPost()
	{
		textureWidth = 64;
		textureHeight = 32;

	      Post = new ModelRenderer(this, 0, 0);
	      Post.addBox(0F, 0F, 0F, 4, 16, 4);
	      Post.setRotationPoint(-2F, 8F, -2F);
	      Post.setTextureSize(64, 32);
	      Post.mirror = true;
	      setRotation(Post, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		Post.render(f5);
	}

	public void render(TileEntity te, float f) {

		float f5 = 0.0625F;
		setRotationAngles(te, f);
		Post.render(f5);
	}

	public void setRotationAngles(TileEntity te, float f) {
		super.setRotationAngles(0f, 0f, 0f, 0f, 0f, f, null);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
