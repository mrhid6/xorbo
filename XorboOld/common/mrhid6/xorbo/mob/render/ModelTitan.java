package mrhid6.xorbo.mob.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;


public class ModelTitan extends ModelBase
{
	//fields
	ModelRenderer RightLeg;
	ModelRenderer Body;
	ModelRenderer LeftLeg;
	ModelRenderer RightArm;
	ModelRenderer LeftArm;
	ModelRenderer Head;

	public ModelTitan(){

		textureWidth = 64;
		textureHeight = 32;

		RightLeg = new ModelRenderer(this, 28, 0);
		RightLeg.addBox(-2F, 0F, -2F, 4, 10, 4);
		RightLeg.setRotationPoint(3F, 14F, 0F);
		RightLeg.setTextureSize(64, 32);
		setRotation(RightLeg, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(-5F, -5F, -2F, 10, 15, 4);
		Body.setRotationPoint(0F, 4F, 0F);
		Body.setTextureSize(64, 32);
		setRotation(Body, 0F, 0F, 0F);
		LeftLeg = new ModelRenderer(this, 28, 0);
		LeftLeg.addBox(-2F, 0F, -2F, 4, 10, 4);
		LeftLeg.setRotationPoint(-3F, 14F, 0F);
		LeftLeg.setTextureSize(64, 32);
		setRotation(LeftLeg, 0F, 0F, 0F);
		RightArm = new ModelRenderer(this, 0, 19);
		RightArm.addBox(0F, 0F, -1.533333F, 3, 10, 3);
		RightArm.setRotationPoint(5F, -1F, 0F);
		RightArm.setTextureSize(64, 32);
		setRotation(RightArm, 0F, 0F, 0F);
		LeftArm = new ModelRenderer(this, 0, 19);
		LeftArm.addBox(-3F, 0F, -1.5F, 3, 10, 3);
		LeftArm.setRotationPoint(-5F, -1F, 0F);
		LeftArm.setTextureSize(64, 32);
		setRotation(LeftArm, 0F, 0F, 0F);
		Head = new ModelRenderer(this, 28, 14);
		Head.addBox(-3F, -5F, -3F, 6, 7, 6);
		Head.setRotationPoint(0F, -3F, 0F);
		Head.setTextureSize(64, 32);
		setRotation(Head, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		RightLeg.render(par7);
		Body.render(par7);
		LeftLeg.render(par7);
		RightArm.render(par7);
		LeftArm.render(par7);
		Head.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);

		this.Head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.Head.rotateAngleX = par5 / (180F / (float)Math.PI);

		this.RightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.3F;
		this.LeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.3F;
		this.RightArm.rotateAngleZ = 0.0F;
		this.LeftArm.rotateAngleZ = 0.0F;

		this.RightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.RightLeg.rotateAngleY = 0.0F;
		this.LeftLeg.rotateAngleY = 0.0F;
	}

}
