package mrhid6.xorbo.triniumlaser;

import mrhid6.xorbo.Utils;
import mrhid6.xorbo.tileentities.models.ModelTLTurret;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class TESRTriniumLaserTurret extends TileEntitySpecialRenderer{

	private ModelTLTurret model;

	public TESRTriniumLaserTurret(){
		this.model = new ModelTLTurret();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float fq) {

		TETriniumLaserTurret tile = (TETriniumLaserTurret)te;

		Tessellator tessellator = Tessellator.instance;

		bindTextureByName("/mrhid6/xorbo/textures/ModelTLTurret.png");
		GL11.glPushMatrix();
		{

			GL11.glPushMatrix();
			{

				GL11.glScalef(1.0F, 1.0F, 1.0F);
				GL11.glTranslatef((float)x + 0.5F, (float)y+1.5f, (float)z + 0.5F);
				GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(-(tile.rotX +90 - tile.vRadX + fq * tile.speedX), 0.0F, 1.0F, 0.0F);
				//System.out.println(tile.rotX);
				this.model.renderBase();
				this.model.renderTurret(tile,(tile.rotZ - tile.vRadZ + fq));
				GL11.glScalef(1.0F, 1.0F, 1.0F);
			}
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
	}

}
