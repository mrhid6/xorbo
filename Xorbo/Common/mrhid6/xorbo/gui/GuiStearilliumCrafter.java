package mrhid6.xorbo.gui;

import mrhid6.xorbo.GridManager;
import mrhid6.xorbo.GridPower;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiStearilliumCrafter extends GuiMain
{
	public ContainerStearilliumCrafter container;

	public GuiStearilliumCrafter(ContainerStearilliumCrafter container)
	{
		super(container);
		this.container = container;
	}

	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		fontRenderer.drawString("Zoro Furnace", 50, 6, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
		super.drawGuiContainerForegroundLayer(param1, param2);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		//draw your Gui here, only thing you need to change is the path
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);


		this.mc.renderEngine.bindTexture("/mods/xorbo/textures/gui/furnace.png");
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		GridPower grid = GridManager.getGrid(this.container.tileEntity.gridindex);

		if(grid!=null){
			int l = grid.getScaledEnergyStored(102);
			if (l > 0) {
				drawTexturedModalRect(x + 42, y + 61, 0, 166, l, 10);
			}
		}
	}

	@Override
	protected void drawTooltips()
	{
		GridPower grid = GridManager.getGrid(this.container.tileEntity.gridindex);

		if(grid==null)
			return;

		if ((this.mousex >= 42) && (this.mousex < 144) && (this.mousey >= 61) && (this.mousey < 71)){
			drawToolTip("" + (int)grid.getEnergyStored()+ " / " + (int)grid.getMaxEnergy() + " MJ");
		}

	}
}