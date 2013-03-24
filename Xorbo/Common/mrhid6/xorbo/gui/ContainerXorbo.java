package mrhid6.xorbo.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public abstract class ContainerXorbo extends Container
{

	public boolean transferToSlots(ItemStack stack, int startIndex, int endIndex, boolean lookBackwards)
	{
		return mergeItemStack(stack, startIndex, endIndex, lookBackwards);
	}

	public abstract boolean canInteractWith(EntityPlayer player);
}
