package mrhid6.xorbo.triniumlaser;

import mrhid6.xorbo.gui.GuiMain;
import mrhid6.xorbo.tileentities.TETriniumCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiTriniumLaser extends GuiMain
{
	private TETriniumLaserBase tileEntity;
	private ContainerTLaser container;

	public GuiTriniumLaser(ContainerTLaser container)
	{
		super(container);
		this.container = container;
		this.tileEntity = container.tileEntity;
		
		this.xSize = 176;
		this.ySize = 228;
	}

	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		fontRenderer.drawString("Trinium Laser", 55, 8, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
		super.drawGuiContainerForegroundLayer(param1, param2);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		
		//draw your Gui here, only thing you need to change is the path
		int texture = mc.renderEngine.getTexture("/mrhid6/xorbo/textures/gui/laser.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		int l = tileEntity.getScaledEnergyStored(108);
		
		if (l > 0){
			drawTexturedModalRect(x + 68, y + 22 + 108 - l, 176, 108-l, 12, l);
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int mouseButton) {
		super.mouseClicked(x, y, mouseButton);

		handleMouseClicked(this.mousex,this.mousey,mouseButton);
	}

	public boolean handleMouseClicked(int x, int y, int mouseButton){
		if(x>10 && x<24 && y>10 && y<24){
			if(tileEntity.transmitpower){
				tileEntity.setTransmitInfo(false);
			}else{
				tileEntity.setTransmitInfo(true);
			}
		}

		return true;
	}

	@Override
	protected void drawTooltips()
	{
		if ((this.mousex >= 68) && (this.mousex < 80) && (this.mousey >= 22) && (this.mousey < 130)){
			drawToolTip("" + (int)tileEntity.getEnergy() + " / " + (int)tileEntity.getMaxEnergy() + " MJ");
		}
		if ((this.mousex >= 10) && (this.mousex < 24) && (this.mousey >= 10) && (this.mousey < 24)){
			drawToolTip("Transmit: "+((tileEntity.transmitpower)?"On":"Off")+"");
		}

	}
}