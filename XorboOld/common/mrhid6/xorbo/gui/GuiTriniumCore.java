package mrhid6.xorbo.gui;

import mrhid6.xorbo.tileentities.TETriniumCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiTriniumCore extends GuiMain
{
	private TETriniumCore tileEntity;
	private ContainerTriniumCore container;

	public GuiTriniumCore(ContainerTriniumCore container)
	{
		super(container);
		this.container = container;
		tileEntity = container.tileEntity;
	}

	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		fontRenderer.drawString("Trinium Core", 55, 6, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
		super.drawGuiContainerForegroundLayer(param1, param2);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{

		//draw your Gui here, only thing you need to change is the path
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.func_98187_b("/mods/xorbo/textures/gui/furnace.png");
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		int l = tileEntity.getScaledEnergyStored(102);
		if (l > 0) {
			drawTexturedModalRect(x + 42, y + 61, 0, 166, l, 10);
		}
		
		if(tileEntity.transmitpower){
			if(this.mousex>10 && this.mousex<24 && this.mousey>10 && this.mousey<24){
				drawIcon("/mrhid6/xorbo/textures/gui/icons.png",21,x+10,y+10);
			}else{
				drawIcon("/mrhid6/xorbo/textures/gui/icons.png",5,x+10,y+10);	
			}
		}else{
			if(this.mousex>10 && this.mousex<24 && this.mousey>10 && this.mousey<24){
				drawIcon("/mrhid6/xorbo/textures/gui/icons.png",20,x+10,y+10);
			}else{
				drawIcon("/mrhid6/xorbo/textures/gui/icons.png",4,x+10,y+10);	
			}
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
		if ((this.mousex >= 42) && (this.mousex < 144) && (this.mousey >= 61) && (this.mousey < 71)){
			drawToolTip("" + (int)tileEntity.getEnergy() + " / " + (int)tileEntity.getMaxEnergy() + " MJ");
		}
		if ((this.mousex >= 10) && (this.mousex < 24) && (this.mousey >= 10) && (this.mousey < 24)){
			drawToolTip("Transmit: "+((tileEntity.transmitpower)?"On":"Off")+"");
		}

	}
}