package mrhid6.xorbo.triniumlaser;

import org.lwjgl.opengl.GL11;

import mrhid6.xorbo.tileentities.models.ModelTLBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class TESRTriniumLaserBase extends TileEntitySpecialRenderer{

	private ModelTLBase model;

	public TESRTriniumLaserBase(){
		this.model = new ModelTLBase();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		{
			bindTextureByName("/mrhid6/xorbo/textures/ModelTLBase.png");
			
			GL11.glTranslatef((float)x + 0.5F, (float)y+1.5f, (float)z + 0.5F);
			GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			this.model.render();
			GL11.glScalef(1.0F, 1.0F, 1.0F);
		}
		GL11.glPopMatrix();
	}

}
