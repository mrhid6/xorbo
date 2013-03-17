package mrhid6.xorbo.render;


import java.util.Random;

import mrhid6.xorbo.models.ModelTSun;
import mrhid6.xorbo.tileEntity.TETriniumSun;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class RenderTriniumSun extends TileEntitySpecialRenderer
{
	private ModelTSun model;

	public RenderTriniumSun(){
		this.model = new ModelTSun();
	}

	private void drawCrystal(float x, float y, float z, float partialTick, Random rand, TETriniumSun tile)
	{
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		float bob = (tile.bob)?MathHelper.sin(p.ticksExisted / 12.0F) * 0.046F:-0.2f;
		
		
		GL11.glPushMatrix();
		bindTextureByName("/mrhid6/xorbo/textures/SShard.png");
		GL11.glTranslatef(x + 0.5F, y-0.5f, z + 0.5F);
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		this.model.render();
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
		
		
		GL11.glPushMatrix();

		GL11.glEnable(2977);
		GL11.glEnable(3042);
		GL11.glEnable(32826);
		GL11.glBlendFunc(770, 771);
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(220);

		bindTextureByName("/mrhid6/xorbo/textures/SShard.png");

		for (int a = 0; a < 1; a++) {
			float pulse = MathHelper.sin(p.ticksExisted / (10.0F + a)) * 0.1F;
			GL11.glPushMatrix();
			GL11.glTranslatef(x + 0.5F, y + bob-0.5F, z + 0.5F);
			GL11.glRotatef( ((rand.nextInt(5) + 45) * a + partialTick + p.ticksExisted)*(tile.speed*1.5F) % 360, 0.0F, 1.0F, 0.0F);
			GL11.glColor4f(0.9F + pulse, 0.2F + pulse, 0.9F + pulse, 1.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			this.model.renderInner();
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
		}

		GL11.glDisable(32826);
		GL11.glDisable(3042);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPopMatrix();
	}

	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f)
	{
		TETriniumSun tile = (TETriniumSun) te;
		
		/*if(tile.canseesun){
			tile.speed = 20;
			tile.bob = true;
		}else{
			tile.speed = 0;
			tile.bob = false;
		}*/
		drawCrystal((float)x, (float)y, (float)z, f, new Random(te.xCoord * te.yCoord + te.zCoord),tile);
	}
}