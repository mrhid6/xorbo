package mrhid6.xorbo.render;

import mrhid6.xorbo.proxy.commonProxy;
import mrhid6.xorbo.tileEntity.TECableBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

public class RenderCableBase extends TileEntitySpecialRenderer{

	RenderBlocks blkRenderer = new RenderBlocks();

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
		TECableBase cable = (TECableBase)te;

		if(cable.worldObj!=null){

			Tessellator tess = Tessellator.instance;

			ForgeHooksClient.bindTexture(cable.getBlockType().getTextureFile(), 0);

			tess.startDrawingQuads();
			this.blkRenderer.blockAccess = cable.worldObj;
			GL11.glPushMatrix();
			{
				GL11.glTranslated(-cable.xCoord + x, -cable.yCoord + y, -cable.zCoord + z);

				cable.draw(this.blkRenderer);

				tess.draw();
			}
			GL11.glPopMatrix();

		}
	}

}
