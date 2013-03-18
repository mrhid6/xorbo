package mrhid6.xorbo.gui;

import mrhid6.xorbo.tileentities.TEStearilliumGrinder;
import mrhid6.xorbo.tileentities.TETriniumCore;
import mrhid6.xorbo.tileentities.TileZoroFurnace;
import mrhid6.xorbo.tileentities.engine.TileZoroGen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class GuiMain extends GuiContainer{


	protected int mousex;
	protected int mousey;

	public GuiMain(Container par1Container) {
		super(par1Container);
	}

	public void drawIcon(String texture, int iconIndex, int x, int y){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.func_98187_b("/mods/xorbo/textures/gui/icons.png");
		int textureRow = iconIndex >> 4;
		int textureColumn = iconIndex - 16 * textureRow;

		drawTexturedModalRect(x, y, 16 * textureColumn, 16 * textureRow, 16, 16);
	}



	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {

		GL11.glDisable(2896);
		GL11.glDisable(2929);

		drawTooltips();

		GL11.glEnable(2896);
		GL11.glEnable(2929);
	}

	public void drawToolTip(String text){
		drawCreativeTabHoveringText(text,this.mousex,this.mousey);
	}

	protected abstract void drawTooltips();

	@Override
	public void handleMouseInput() {

		int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
		int y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight-1;

		this.mousex = (x - (this.width - this.xSize) /2);
		this.mousey = (y - (this.height - this.ySize) /2);

		super.handleMouseInput();
	}


}
