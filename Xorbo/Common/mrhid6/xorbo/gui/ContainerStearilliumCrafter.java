package mrhid6.xorbo.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mrhid6.xorbo.tileEntity.TEStearilliumCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerStearilliumCrafter extends ContainerXorbo
{
	public float energy = -1;
	protected TEStearilliumCrafter tileEntity;

	public ContainerStearilliumCrafter(EntityPlayer inventory, TEStearilliumCrafter te)
	{
		tileEntity = te;
		
		int l;
        int i1;
		
		for (l = 0; l < 3; ++l)
        {
            for (i1 = 0; i1 < 3; ++i1)
            {
                this.addSlotToContainer(new Slot(this.tileEntity, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
            }
        }

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventory.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++)
			addSlotToContainer(new Slot(inventory.inventory, i, 8 + i * 18, 142));
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int i)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(i);

		int invTile = this.tileEntity.inventory.length;
		int invPlayer = invTile + 27;
		int invFull = invTile + 36;

		if ((slot != null) && (slot.getHasStack())) {
			ItemStack stackInSlot = slot.getStack();
			itemstack = stackInSlot.copy();

			if (i == 1) {
				if (!mergeItemStack(stackInSlot, invTile, invFull, true))
					return null;
			}
			else if (i != 0) {
				if (this.tileEntity.getResultFor(stackInSlot) != null) {
					if (!mergeItemStack(stackInSlot, 0, 1, false))
						return null;
				}
				else if ((i >= invTile) && (i < invPlayer)) {
					if (!mergeItemStack(stackInSlot, invPlayer, invFull, false))
						return null;
				}
				else if ((i >= invPlayer) && (i < invFull) && (!mergeItemStack(stackInSlot, invTile, invPlayer, false)))
					return null;
			}
			else if (!mergeItemStack(stackInSlot, invTile, invFull, false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0)
				slot.putStack((ItemStack)null);
			else {
				slot.onSlotChanged();
			}

			if (stackInSlot.stackSize == itemstack.stackSize) {
				return null;
			}
			slot.onPickupFromSlot(player, stackInSlot);
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
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int i, int j)
	{
		super.updateProgressBar(i, j);
		tileEntity.receiveGuiNetworkData(i, j);
	}
	
	
}