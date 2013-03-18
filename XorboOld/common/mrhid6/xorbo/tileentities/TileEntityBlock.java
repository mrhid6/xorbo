package mrhid6.xorbo.tileentities;

import mrhid6.xorbo.Utils;
import mrhid6.xorbo.network.PacketUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityBlock extends TileRoot implements IInventory
{
	private int facing = 0;
	public boolean loaded = false;
	public boolean isActive;
	public ItemStack[] inventory;
	public String invName;

	public int getFacing()
	{
		return this.facing;
	}
	public void setFacing(int side)
	{
		this.facing = side;
	}

	public boolean getActive()
	{
		return this.isActive;
	}

	public void setActive(boolean bol)
	{
		this.isActive = bol;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int var1)
	{
		return (var1>=this.inventory.length)?null:this.inventory[var1];
	}

	@Override
	public ItemStack decrStackSize(int i, int amt)
	{
		if (this.inventory[i] != null)
		{
			if (this.inventory[i].stackSize <= amt)
			{
				ItemStack itemstack = this.inventory[i];
				this.inventory[i] = null;
				return itemstack;
			}

			ItemStack itemstack1 = this.inventory[i].splitStack(amt);

			if (this.inventory[i].stackSize == 0)
			{
				this.inventory[i] = null;
			}

			return itemstack1;
		}

		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return this.inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		this.inventory[i] = itemstack;

		if ((itemstack != null) && (itemstack.stackSize > getInventoryStackLimit()))
		{
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		return invName;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1){
		if (this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this){
			return false;
		}
		return var1.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}
	
	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT(data);

		NBTTagList tagList = data.getTagList("inventory");

		this.inventory = new ItemStack[getSizeInventory()];

		for (int i = 0; i < tagList.tagCount(); i++){

			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			int slot = tag.getInteger("slot");

			if (slot >= 0 && slot < this.inventory.length){
				this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}

	}
	
	public void writeToNBT(NBTTagCompound data){
		super.writeToNBT(data);

		NBTTagList itemList = new NBTTagList();

		for (int i = 0; i < this.inventory.length; i++){
			ItemStack stack = this.inventory[i];

			if (stack != null){

				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("slot", i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}

		data.setTag("inventory", itemList);
	}
	
	public void sendGuiNetworkData(Container container, ICrafting iCrafting)
	{
		if (((iCrafting instanceof EntityPlayer)) && 
				(Utils.isServerWorld(this.worldObj)))
			PacketUtils.sendToPlayer((EntityPlayer)iCrafting, getDescriptionPacket());
	}

	
}
