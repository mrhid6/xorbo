package mrhid6.xorbo.triniumlaser;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.gui.ContainerXorbo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTLaser extends ContainerXorbo{
	public float energy = -1;
	protected TETriniumLaserBase tileEntity;

	public ContainerTLaser(EntityPlayer inventory, TETriniumLaserBase te)
	{
		tileEntity = te;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				addSlotToContainer(new Slot(this.tileEntity, j + i * 3, 8 + j * 18, 23 + i * 18));
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventory.inventory, j + i * 9 + 9, 8 + j * 18, 146 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++)
			addSlotToContainer(new Slot(inventory.inventory, i, 8 + i * 18, 204));
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int i)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(i);
		if ((slot != null) && (slot.getHasStack()))
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (i < this.tileEntity.inventory.length)
			{
				if (!mergeItemStack(itemstack1, this.tileEntity.inventory.length, this.inventorySlots.size(), true)){
					return null;
				}
			}
			else if (!mergeItemStack(itemstack1, 0, this.tileEntity.inventory.length, false)){
				return null;
			}
			if (itemstack1.stackSize == 0){
				slot.putStack(null);
			}else{
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
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

		this.energy = this.tileEntity.getEnergy();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int i, int j)
	{
		super.updateProgressBar(i, j);

		tileEntity.receiveGuiNetworkData(i, j);
	}
}
