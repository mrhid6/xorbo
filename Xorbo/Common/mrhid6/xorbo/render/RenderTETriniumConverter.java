package mrhid6.xorbo.render;

import mrhid6.xorbo.models.ModelTConverter;
import mrhid6.xorbo.tileEntity.TETriniumConverter;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class RenderTETriniumConverter extends TileEntitySpecialRenderer {

	private ModelTConverter model;
	public static int renderId;

	public RenderTETriniumConverter(){
		model = new ModelTConverter();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

		if(!(tileentity instanceof TETriniumConverter) || tileentity==null)
			return;

		if(tileentity.worldObj==null){
			bindTextureByName("/mods/xorbo/textures/blocks/TriniumConverter.png");
			GL11.glPushMatrix();
			{
				GL11.glTranslatef((float)x + 0.5F, (float)y+1.5f, (float)z + 0.5F);
				GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
				renderAll();
			}
			GL11.glPopMatrix();
			return;
		}

		TETriniumConverter te = (TETriniumConverter)tileentity;
		te.updateConnections();
		bindTextureByName("/mods/xorbo/textures/blocks/TriniumConverter.png");
		GL11.glPushMatrix();
		{
			GL11.glTranslatef((float)x + 0.5F, (float)y+1.5f, (float)z + 0.5F);
			GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
			renderMain();
			if(te.connections[2]==true){
				renderNorth();
				GL11.glScalef(1.0F, 1.0F, 1.0F);
			}
			if(te.connections[3]==true){
				renderSouth();
				GL11.glScalef(1.0F, 1.0F, 1.0F);
			}
			if(te.connections[4]==true){
				renderWest();
				GL11.glScalef(1.0F, 1.0F, 1.0F);
			}
			if(te.connections[5]==true){
				renderEast();
				GL11.glScalef(1.0F, 1.0F, 1.0F);
			}
			GL11.glScalef(1.0F, 1.0F, 1.0F);
		}
		GL11.glPopMatrix();
	}

	public void renderAll(){
		renderMain();
		renderNorth();
		renderWest();
		renderSouth();
		renderEast();
	}
	public void renderMain(){
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		this.model.render();
	}

	public void renderNorth(){
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0.0F, -0.015F, 0.0001F);
			GL11.glScalef(1.0F, 1.015F, 1.0F);
			this.model.renderNorth();
			GL11.glScalef(1.0F, 1.0F, 1.0F);
		}
		GL11.glPopMatrix();
	}

	public void renderSouth(){
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0.0F, -0.016F, -0.0001F);
			GL11.glScalef(1.0F, 1.016F, 1.0F);
			this.model.renderSouth();
			GL11.glScalef(1.0F, 1.0F, 1.0F);
		}
		GL11.glPopMatrix();
	}

	public void renderEast(){
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0.0001F, -0.017F, 0.0F);
			GL11.glScalef(1.0F, 1.017F, 1.0F);
			this.model.renderEast();
			GL11.glScalef(1.0F, 1.0F, 1.0F);
		}
		GL11.glPopMatrix();
	}
	public void renderWest(){
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(-0.0001F, -0.018F, 0.0F);
			GL11.glScalef(1.0F, 1.018F, 1.0F);
			this.model.renderWest();
			GL11.glScalef(1.0F, 1.0F, 1.0F);
		}
		GL11.glPopMatrix();
	}
}
