package mrhid6.xorbo.render;

import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import mrhid6.xorbo.block.ModBlocks;
import mrhid6.xorbo.items.ModItems;
import mrhid6.xorbo.tileEntity.TEZoroController;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderTEZoroController extends TileEntitySpecialRenderer {

	private Minecraft mc;
	private RenderBlocks renderBlocks;
	private RenderItem renderItems;

	public RenderTEZoroController(){
		mc = FMLClientHandler.instance().getClient();
		renderBlocks = new RenderBlocks();
		renderItems  = new RenderItem() {
			public boolean shouldBob() { return false; }
			public boolean shouldSpreadItems() { return false; }
		};
		renderItems.setRenderManager(RenderManager.instance);
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
		
		if (te == null || !(te instanceof TEZoroController)) 
			return;
			
		int count = 0;
		TEZoroController tpb = (TEZoroController) te;
		try{
			renderBlocks.renderBlockByRenderType(ModBlocks.zoroController, (int) x, (int) y, (int) z);
		} catch (NullPointerException ex){
			count++;
			if(count > 2) throw ex;
			renderBlocks = new RenderBlocks(tpb.worldObj);
		}	

		if (tpb.worldObj.getBlockId(tpb.xCoord, tpb.yCoord + 1, tpb.zCoord) == 0 && tpb.worldObj.getClosestPlayer(tpb.xCoord, tpb.yCoord, tpb.zCoord, 15) != null && !mc.isGamePaused) {

			ItemStack stack = new ItemStack(ModItems.zoroBucket,1);

			EntityItem ei = new EntityItem(tpb.worldObj);
			ei.hoverStart = 0f;
			ei.setEntityItemStack(stack);
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			glPushMatrix();
			{
				glEnable(32826 /* rescale */);
				glTranslatef((float) x, (float) y, (float) z);

				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 170F, 170F);
				glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

				float rotational = (float) (Minecraft.getSystemTime()) / (3000.0F) * 300.0F;


				if(!ei.getEntityItem().equals(stack)){
					return;
				}
				if(stack.itemID < Block.blocksList.length && Block.blocksList[stack.itemID] != null && Block.blocksList[stack.itemID].blockID != 0){
					glPushMatrix();
					{
						glTranslatef(0.5F, 1.2F, 0.5F);
						glRotatef(rotational / 5, 0F, 1.0F, 0F);
						renderItems.doRenderItem(ei, 0, 0, 0, 0, 0);
					}
					glPopMatrix();
				}else{
					glPushMatrix();
					{
						glTranslatef(0.5F, 1.1F, 0.5F);
						glScalef(0.6F, 0.6F, 0.6F);
						glRotatef(rotational / 5, 0F, 1.0F, 0F);
						renderItems.doRenderItem(ei, 0, 0, 0, 0, 0);
					}
					glPopMatrix();
				}
				glDisable(32826 /* scale */);
			}
			glPopMatrix();
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}

	}
}
