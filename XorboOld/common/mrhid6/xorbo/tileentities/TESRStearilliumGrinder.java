package mrhid6.xorbo.tileentities;

import java.util.Random;

import mrhid6.xorbo.models.ModelGrinder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class TESRStearilliumGrinder extends TileEntitySpecialRenderer
{
	private ModelGrinder model;

	public TESRStearilliumGrinder(){
		this.model = new ModelGrinder();
	}

	private void drawCrystal(float x, float y, float z, float partialTick, Random rand, TEStearilliumGrinder tile)
	{
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		
		
		GL11.glPushMatrix();
		bindTextureByName("/mrhid6/xorbo/textures/ModelGrinder.png");
		GL11.glTranslatef(x + 0.5F, y-0.5f, z + 0.5F);
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		this.model.render(tile.isActive);
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
		
		
		GL11.glPushMatrix();

		GL11.glEnable(2977);
		GL11.glEnable(3042);
		GL11.glEnable(32826);
		GL11.glBlendFunc(770, 771);
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(220);

		bindTextureByName("/mrhid6/xorbo/textures/ModelGrinder.png");
		
		//float test = tile.myProvider.getEnergyStored();
		//System.out.println(test);
		
		for (int a = 0; a < 1; a++) {
			float pulse = MathHelper.sin(p.ticksExisted / (10.0F + a)) * 0.1F;
			GL11.glPushMatrix();
			GL11.glTranslatef(x + 0.5F, y +0.45f, z + 0.5F);
			GL11.glRotatef( ((rand.nextInt(5) + 45) * a + partialTick + p.ticksExisted)*(tile.speed*1.5F) % 360, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			this.model.renderBlades();
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
		
		
		TEStearilliumGrinder tile = (TEStearilliumGrinder) te;
		if(tile.canStart()){
			tile.speed=30;
		}else{
			tile.speed=0;
		}
		
		drawCrystal((float)x, (float)y, (float)z, f, new Random(te.xCoord * te.yCoord + te.zCoord),(TEStearilliumGrinder) te);
	}
}