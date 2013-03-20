package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Utils;
import mrhid6.xorbo.block.ModBlocks;
import mrhid6.xorbo.interfaces.IPacketXorHandler;
import mrhid6.xorbo.network.PacketTile;
import mrhid6.xorbo.network.PacketUtils;
import mrhid6.xorbo.network.Payload;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;

public abstract class TEMachineBase extends TEBlock implements ISidedInventory, IPacketXorHandler
{
	public ItemStack[] processInv;
	byte facing;
	byte[] sideConfig;
	public boolean wasActive;
	protected float processCur;
	protected float processEnd;
	protected static int descPacketId;

	public TEMachineBase()
	{

	}
	
	public static boolean setDescPacketId(int id){
		if (id == 0) {
			return false;
		}
		descPacketId = id;
		return true;
	}

	public int getScaledProgress(int scale)
	{
		if (this.processEnd == 0.0F) {
			return 0;
		}
		return (int)(this.processCur * scale / this.processEnd);
	}


	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1){
		if (this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this){
			return false;
		}

		return var1.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
	}

	@Override
	public void openChest(){}

	@Override
	public void closeChest(){}

	@Override
	public Packet getDescriptionPacket() {
		Payload payload = new Payload(2, 0, 1, 3, 0);

		payload.boolPayload[0] = this.isActive;
		payload.boolPayload[1] = this.transmitpower;

		payload.intPayload[0] = this.gridindex;

		if(getGrid()!=null){
			//System.out.println("grid is correct");
			payload.floatPayload[0] = getGrid().getEnergyStored();
		}
		payload.floatPayload[1] = this.processCur;
		payload.floatPayload[2] = this.processEnd;

		PacketTile packet = new PacketTile(descPacketId,this.xCoord,this.yCoord,this.zCoord,payload);
		return packet.getPacket();
	}

	@Override
	public void handleTilePacket(PacketTile packet) {

		this.isActive = packet.payload.boolPayload[0];

		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);
		this.worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, ModBlocks.zoroFurnace.blockID);

		if (Utils.isServerWorld(this.worldObj))
			PacketUtils.sendToPlayers(getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 192);

	}

	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT(data);

		this.processCur = data.getFloat("process.cur");
		this.processEnd = data.getFloat("process.end");
		if(getGrid()!=null)
			getGrid().setEnergyStored(data.getFloat("grid.power"));

	}

	public int getScaledEnergyStored(int scale)
	{
		if(myGrid!=null){
			return Math.round(this.myGrid.getEnergyStored() * scale / this.myGrid.getMaxEnergy());
		}
		return 0;
	}

	public void writeToNBT(NBTTagCompound data){
		super.writeToNBT(data);

		data.setFloat("process.cur", this.processCur);
		data.setFloat("process.end", this.processEnd);
		
		if(getGrid()!=null)
			data.setFloat("grid.power", getGrid().getEnergyStored());
	}
}
