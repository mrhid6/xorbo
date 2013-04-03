package mrhid6.xorbo.render;

import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import mrhid6.xorbo.block.ModBlocks;
import mrhid6.xorbo.items.ModItems;
import mrhid6.xorbo.tileEntity.TEZoroController;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderTEZoroController extends TileEntitySpecialRenderer {

	private Minecraft mc;
	private RenderBlocks renderBlocks;
	private RenderItem renderItems;
	
	private float rotateAngle = 4;

	public RenderTEZoroController() {
		mc = FMLClientHandler.instance().getClient();
		renderBlocks = new RenderBlocks();
		renderItems = new RenderItem(){

			@Override
			public boolean shouldBob() {
				return false;
			}

			@Override
			public boolean shouldSpreadItems() {
				return false;
			}
		};
		renderItems.setRenderManager(RenderManager.instance);
	}

	@Override
	public void renderTileEntityAt( TileEntity te, double x, double y, double z, float f ) {

		if (te == null || !(te instanceof TEZoroController)) {
			return;
		}

		int count = 0;
		TEZoroController tpb = (TEZoroController) te;
		try {
			renderBlocks.renderBlockByRenderType(ModBlocks.zoroController, (int) x, (int) y, (int) z);
		} catch (NullPointerException ex) {
			count++;
			if (count > 2) {
				throw ex;
			}
			renderBlocks = new RenderBlocks(tpb.worldObj);
		}

		if (tpb.worldObj.getBlockId(tpb.xCoord, tpb.yCoord + 1, tpb.zCoord) == 0 && tpb.worldObj.getClosestPlayer(tpb.xCoord, tpb.yCoord, tpb.zCoord, 15) != null && !mc.isGamePaused) {

			EntityPlayer p = mc.thePlayer;
			
			ItemStack stack = new ItemStack(ModItems.zoroStaff, 1);

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

				float rotational = (Minecraft.getSystemTime()) / (3000.0F) * 300.0F;

				if (!ei.getEntityItem().equals(stack)) {
					return;
				}
				if (stack.itemID < Block.blocksList.length && Block.blocksList[stack.itemID] != null && Block.blocksList[stack.itemID].blockID != 0) {
					glPushMatrix();
					{
						glTranslatef(0.5F, 1.2F, 0.5F);
						glRotatef(rotational / 5, 0F, 1.0F, 0F);
						renderItems.doRenderItem(ei, 0, 0, 0, 0, 0);
					}
					glPopMatrix();
				} else {
					glPushMatrix();
					{
						float bob = MathHelper.sin(p.ticksExisted / (10.0F)) * 0.02F;
						
						RenderEngine renderEngine = RenderManager.instance.renderEngine;
						Tessellator tessellator = Tessellator.instance;
						
						String texture = ((stack.getItemSpriteNumber() == 0) ? "/terrain.png" : "/gui/items.png");
						renderEngine.bindTexture(texture);
						Icon icon = stack.getIconIndex();
						
						glTranslatef(0.5F, 1.1F+bob, 0.5F);
						glRotatef(90F, 1F, 0.0F, 0F);
						GL11.glScalef(0.6F, 0.6F, 0.6F);
						
						float fa = icon.getMinU();
						float f1 = icon.getMaxU();
						float f2 = icon.getMinV();
						float f3 = icon.getMaxV();
						GL11.glEnable(GL12.GL_RESCALE_NORMAL);
						//GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
						GL11.glTranslatef(-0.5F, -0.5F, 1 / 32.0F);
						ItemRenderer.renderItemIn2D(tessellator, f1, f2, fa, f3, icon.getSheetWidth(), icon.getSheetHeight(), 0.0625F);

						
						
						GL11.glDepthFunc(GL11.GL_EQUAL);
						GL11.glDisable(GL11.GL_LIGHTING);
						renderEngine.bindTexture("%blur%/misc/glint.png");
						GL11.glEnable(GL11.GL_BLEND);
						GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
						float f7 = 0.76F;
						GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
						GL11.glMatrixMode(GL11.GL_TEXTURE);
						GL11.glPushMatrix();
						float f8 = 0.3F;
						GL11.glScalef(f8, f8, f8);
						float f9 = Minecraft.getSystemTime() % 3000L / 3000.0F * 8.0F;
						GL11.glTranslatef(f9, 0.0F, 0.0F);
						GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
						ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
						GL11.glPopMatrix();
						GL11.glPushMatrix();
						GL11.glScalef(f8, f8, f8);
						f9 = Minecraft.getSystemTime() % 4873L / 4873.0F * 8.0F;
						GL11.glTranslatef(-f9, 0.0F, 0.0F);
						GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
						ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
						GL11.glPopMatrix();
						GL11.glMatrixMode(GL11.GL_MODELVIEW);
						GL11.glDisable(GL11.GL_BLEND);
						GL11.glEnable(GL11.GL_LIGHTING);
						GL11.glDepthFunc(GL11.GL_LEQUAL);
						
						//renderItems.doRenderItem(ei, 0, 0, 0, 0, 0);
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
