package mrhid6.xorbo.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mrhid6.xorbo.tileentities.TETriniumChillerCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;

public class ContainerTChiller extends ContainerXorbo{
	
	private TETriniumChillerCore tileEntity;

	public ContainerTChiller(EntityPlayer player, TETriniumChillerCore tile) {
		tileEntity = tile;
		
		addSlotToContainer(new Slot(this.tileEntity, 0, 56, 24));
		addSlotToContainer(new SlotFurnace(player, this.tileEntity, 1, 116, 33));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++)
			addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); i++)
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);
			
			tileEntity.sendGuiNetworkData(this, icrafting);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int i, int j)
	{
		super.updateProgressBar(i, j);

		tileEntity.receiveGuiNetworkData(i, j);
	}

}
