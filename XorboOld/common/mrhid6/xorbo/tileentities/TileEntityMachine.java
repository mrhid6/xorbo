package mrhid6.xorbo.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public abstract class TileEntityMachine extends TileEntityBlock implements ISidedInventory
{
	public ItemStack[] processInv;
	byte facing;
	byte[] sideConfig;
	public boolean wasActive;
	protected float processCur;
	protected float processEnd;
	private static int descPacketId;

	public TileEntityMachine()
	{
		
	}

	public int getScaledProgress(int scale)
	{
		if (this.processEnd == 0.0F) {
			return 0;
		}
		return (int)(this.processCur * scale / this.processEnd);
	}

	  protected abstract boolean canStart();

	  protected abstract boolean canFinish();

	  protected abstract void processStart();

	  protected abstract void processFinish();


	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1)
	{
		if (this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
		{
			return false;
		}

		return var1.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
	}

	@Override
	public void openChest()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void closeChest()
	{
		// TODO Auto-generated method stub
	}

	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT(data);
		NBTTagList tagList = data.getTagList("proinventory");
		
		this.processInv = new ItemStack[1];
		
		this.processCur = data.getFloat("process.cur");
		this.processEnd = data.getFloat("process.end");
		
		for (int i = 0; i < tagList.tagCount(); i++){
			
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			int slot = tag.getInteger("proslot");
			
			if (slot >= 0 && slot < this.processInv.length){
				this.processInv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}

	}

	public void writeToNBT(NBTTagCompound data){
		super.writeToNBT(data);

		NBTTagList itemList = new NBTTagList();
		
		data.setFloat("process.cur", this.processCur);
		data.setFloat("process.end", this.processEnd);
		for (int i = 0; i < this.processInv.length; i++){
			ItemStack stack = this.processInv[i];
			
			if (stack != null){
				
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("proslot", i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		data.setTag("proinventory", itemList);
	}

	@Override
	public int getStartInventorySide(ForgeDirection side)
	{
		if(side == ForgeDirection.UP){
			return 0;
		}else if(side == ForgeDirection.DOWN){
			return 0;
		}else{
			return 1;
		}
	}

	@Override
	public int getSizeInventorySide(ForgeDirection side)
	{
		return 1;
	}
}
