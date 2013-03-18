package mrhid6.xorbo.tileentities;

import java.util.Random;

import mrhid6.xorbo.tileentities.models.ModelTriniumCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class TESRTriniumCore extends TileEntitySpecialRenderer
{
	private ModelTriniumCore model;

	public TESRTriniumCore(){
		this.model = new ModelTriniumCore();
	}

	private void drawCrystal(float x, float y, float z, float partialTick, Random rand, TETriniumCore tile)
	{
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;


		GL11.glPushMatrix();
		bindTextureByName("/mrhid6/xorbo/textures/ModelTCore.png");
		GL11.glTranslatef(x + 0.5F, y-0.5f, z + 0.5F);
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		this.model.render();
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();


		GL11.glPushMatrix();
		{
			GL11.glEnable(2977);
			GL11.glEnable(3042);
			GL11.glEnable(32826);
			GL11.glBlendFunc(770, 771);
			Tessellator tessellator = Tessellator.instance;
			tessellator.setBrightness(220);

			bindTextureByName("/mrhid6/xorbo/textures/ModelTCore.png");
			float pulse = MathHelper.sin(p.ticksExisted / (10.0F + 0)) * 0.1F;

			float en = tile.getScaledEnergyStored(100);
			float div = (en>0)?(en/100):0;

			if(en>=1){
				GL11.glPushMatrix();
				{
					float newy = y-(div*0.5F)+0.02F;

					GL11.glTranslatef(x + 0.5F, newy, z + 0.5F);
					GL11.glScalef(0.98F, div-0.02F, 0.98F);

					GL11.glColor4f(0.8F + pulse, 0.8F + pulse, 0.8F + pulse, 1.0F);
					this.model.renderInner();
					GL11.glScalef(1.0F, 1.0F, 1.0F);
				}
				GL11.glPopMatrix();
			}

			GL11.glDisable(32826);
			GL11.glDisable(3042);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
		GL11.glPopMatrix();
	}

	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f)
	{
		drawCrystal((float)x, (float)y, (float)z, f, new Random(te.xCoord * te.yCoord + te.zCoord),(TETriniumCore) te);
	}
}